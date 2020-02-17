package com.debuff.zeus;

import android.Manifest;
import android.os.Bundle;
import android.widget.ImageView;

import com.baidu.mapapi.map.MapView;
import com.zeus.core.base.BaseActivity;
import com.zeus.core.library.map.MapManager;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private MapManager mMapManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        mPermissionProcessor.checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION);
        mMapManager = new MapManager(mBmapView);
        mMapManager.startLocation();
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

    @OnClick(R.id.location_view)
    public void onViewClicked() {
        mMapManager.location();
    }
}
