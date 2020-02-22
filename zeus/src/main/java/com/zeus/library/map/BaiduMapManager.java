package com.zeus.library.map;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;

import java.util.ArrayList;
import java.util.List;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 百度地图
 ***************************************************/
public class BaiduMapManager implements IMap {

    private BaiduMap mBaiduMap;
    private MapView mMapView;
    private LocationClient mLocationClient;

    private BaiduLocation mBaiduLocation;

    private List<ILocationListener> mListeners = new ArrayList<>();

    public BaiduMapManager(MapView mapView) {
        mMapView = mapView;
        mBaiduMap = mMapView.getMap();
        mBaiduLocation = new BaiduLocation(mMapView);
    }

    @Override
    public void start() {
        mBaiduMap.setMyLocationEnabled(true);
        //定位初始化
        mLocationClient = new LocationClient(mMapView.getContext());

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        mLocationClient.registerLocationListener(mBaiduLocation);
        //开启地图定位图层
        mLocationClient.start();
    }

    @Override
    public void location() {
        mBaiduLocation.updateMapViewLocation(mBaiduLocation.getCurLat(), mBaiduLocation.getCurLng());
    }

    @Override
    public LocationPoint getCurLatLng() {
        return new LocationPoint(mBaiduLocation.getCurLat(), mBaiduLocation.getCurLng());
    }

    @Override
    public void onResume() {
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }


    public void addListener(ILocationListener locationListener) {
        mBaiduLocation.addListener(locationListener);
    }

    public void removeListener(ILocationListener locationListener) {
        mBaiduLocation.removeListener(locationListener);
    }

    @Override
    public void setListenerDelay(int time) {
        mBaiduLocation.setListenerDelay(time);
    }

    @Override
    public Overlay addOverlay(OverlayOptions overlayOptions) {
        return mBaiduLocation.addOverlay(overlayOptions);
    }

}
