package com.debuff.zeus;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.zeus.core.base.BaseActivity;
import com.zeus.library.map.BaiduMapManager;
import com.zeus.library.map.ILocationListener;
import com.zeus.library.map.IMap;
import com.zeus.utils.app.SDCardUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.debuff.zeus.LocationService.LOCATION_FILE_PATH;

/***************************************************
 * Author: Debuff
 * Data: 2017/5/21
 * Description:
 ***************************************************/
public class MapActivity extends BaseActivity {

    //
    @BindView(R.id.bmapView)
    MapView mBmapView;
    @BindView(R.id.location_view)
    ImageView mLocationView;
    @BindView(R.id.start_collect)
    TextView mStartCollect;
    @BindView(R.id.query_today_path)
    TextView mQueryTodayPath;
    private IMap mMapManager;

    boolean isStart = false;
    private Intent mLocationIntentService;

    List<String> mLocationStr = new ArrayList<>();

    List<LatLng> points = new ArrayList<>();

    private Handler drawHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!isStart) {
                return;
            }
            //获取所有的数据
            //设置折线的属性
            if (points.size() >= 2) {
                OverlayOptions mOverlayOptions = new PolylineOptions()
                        .width(10)
                        .color(0xAAFF0000)
                        .points(points);
                Overlay overlay = mMapManager.addOverlay(mOverlayOptions);
            }
            drawPathInMapView();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        mPermissionProcessor.checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION);
        mMapManager = new BaiduMapManager(mBmapView);
        mMapManager.start();
        mMapManager.setListenerDelay(1000);
        mMapManager.addListener(new ILocationListener() {
            @Override
            public void update(double lat, double lng) {
                if (!isStart) {
                    return;
                }
                mLocationStr.add("{" + lat + "," + lng + "}|");
                points.add(new LatLng(lat, lng));
                if (mLocationStr.size() > 60) {
                    Intent intent = new Intent("action.zeus.location");
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String s : mLocationStr) {
                        stringBuilder.append(s);
                    }
                    intent.putExtra("lat_lng", stringBuilder.toString());
                    Log.d("MapActivity", stringBuilder.toString());
                    LocalBroadcastManager.getInstance(MapActivity.this).sendBroadcast(intent);
                    mLocationStr.clear();
                }
            }
        });
        mLocationIntentService = new Intent(this, LocationService.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapManager.onDestroy();
    }

    @OnClick({R.id.location_view, R.id.start_collect, R.id.query_today_path})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.location_view:
                mMapManager.location();
                break;
            case R.id.start_collect:
                if (!isStart) {
                    mStartCollect.setText("停止采集");
                    isStart = true;
                    startService(mLocationIntentService);
                    points.clear();
                    points.addAll(getDiskPath());
                    drawPathInMapView();
                } else {
                    mStartCollect.setText("开始采集");
                    isStart = false;
                    SDCardUtils.save(LOCATION_FILE_PATH, parsePointsStr(points), true);
                    stopService(mLocationIntentService);
                }
                break;
            case R.id.query_today_path:
                List<LatLng> latLngs = getDiskPath();
                if (latLngs.size() >= 2) {
                    OverlayOptions mOverlayOptions = new PolylineOptions()
                            .width(10)
                            .color(0xAAFF0000)
                            .points(latLngs);
                    Overlay overlay = mMapManager.addOverlay(mOverlayOptions);
                }
                break;
            default:
                break;
        }
    }

    private String parsePointsStr(List<LatLng> points) {
        StringBuilder result = new StringBuilder();
        for (LatLng p : points) {
            result.append("{")
                    .append(p.latitude)
                    .append(",")
                    .append(p.longitude)
                    .append("}|");
        }
        return result.toString();
    }

    private List<LatLng> getDiskPath() {
        String content = SDCardUtils.getStringContent(LOCATION_FILE_PATH);
        if (TextUtils.isEmpty(content)) {
            return new ArrayList<>();
        }
        String[] pathStr = content.split("\\|");
        points.clear();
        return parseLatLng(Arrays.asList(pathStr));
    }

    private void drawPathInMapView() {
        drawHandler.sendEmptyMessageDelayed(0x11, 3 * 1000);
    }

    private List<LatLng> parseLatLng(List<String> pointsStr) {
        List<LatLng> result = new ArrayList<>();
        for (String point : pointsStr) {
            String[] temp = point.split(",");
            String lat = temp[0].replace("{", "");
            String lng = temp[1].replace("}", "");
            LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
            result.add(latLng);
        }
        return result;
    }
}
