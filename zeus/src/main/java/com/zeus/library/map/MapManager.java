package com.zeus.core.library.map;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class MapManager {

    private BaiduMap mBaiduMap;
    private MapView mMapView;
    private LocationClient mLocationClient;

    private BaiduLocation mBaiduLocation;

    public MapManager(MapView mapView) {
        mMapView = mapView;
        mBaiduMap = mMapView.getMap();
        mBaiduLocation = new BaiduLocation(mMapView);
    }

    public void location() {
        mBaiduLocation.updateMapViewLocation(mBaiduLocation.getCurLat(), mBaiduLocation.getCurLng());
    }

    public void startLocation() {
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

    public void onResume() {
        mMapView.onResume();
    }

    public void onPause() {
        mMapView.onPause();
    }

    public void onDestroy() {
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }

}
