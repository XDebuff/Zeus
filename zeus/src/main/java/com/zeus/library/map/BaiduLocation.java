package com.zeus.library.map;

import android.os.Handler;
import android.os.Message;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 百度地图定位处理
 ***************************************************/
public class BaiduLocation extends BDAbstractLocationListener {

    private boolean isFirstLoc = true;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationPoint mCurrentLocation = new LocationPoint();

    long delayTime = 1000;

    private List<ILocationListener> mListeners = new ArrayList<>();
    private LocationPoint mCurLocationPoint = new LocationPoint();

    private long preTime = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mListeners.size() > 0 && msg.what == 0) {
                for (ILocationListener listener : mListeners) {
                    listener.update(mCurLocationPoint.getLatitude(), mCurLocationPoint.getLongitude());
                }
            }
        }
    };

    public BaiduLocation(MapView mapView) {
        super();
        this.mBaiduMap = mapView.getMap();
        this.mMapView = mapView;
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        //mapView 销毁后不在处理新接收的位置
        if (location == null || mMapView == null) {
            return;
        }
        if (isFirstLoc) {
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            isFirstLoc = false;
            mCurrentLocation.setLatitude(location.getLatitude());
            mCurrentLocation.setLongitude(location.getLongitude());
            updateMapViewLocation(location.getLatitude(), location.getLongitude());
        }
        if (System.currentTimeMillis() - preTime > delayTime) {
            mCurLocationPoint.setLatitude(location.getLatitude());
            mCurLocationPoint.setLongitude(location.getLongitude());
            mHandler.sendEmptyMessage(0);
            preTime = System.currentTimeMillis();
        }

    }

    public void updateMapViewLocation(double lat, double lng) {
        LatLng ll = new LatLng(lat, lng);
        MapStatus.Builder builder = new MapStatus.Builder();
        //设置缩放中心点；缩放比例；
        builder.target(ll).zoom(18.0f);
        //给地图设置状态
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    public double getCurLat() {
        return mCurrentLocation.getLatitude();
    }

    public double getCurLng() {
        return mCurrentLocation.getLongitude();
    }

    public void addListener(ILocationListener locationListener) {
        mListeners.add(locationListener);
    }

    public void removeListener(ILocationListener locationListener) {
        mListeners.remove(locationListener);
    }

    public void setListenerDelay(int time) {
        this.delayTime = time;
    }

    public Overlay addOverlay(OverlayOptions overlayOptions) {
        if (mBaiduMap == null) {
            return null;
        }
        return mBaiduMap.addOverlay(overlayOptions);
    }
}
