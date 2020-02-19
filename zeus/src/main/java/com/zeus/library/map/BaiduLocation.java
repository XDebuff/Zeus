package com.zeus.library.map;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 百度地图定位处理
 ***************************************************/
public class BaiduLocation extends BDAbstractLocationListener {

    private boolean isFirstLoc = true;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationPoint mCurrentLocaiton = new LocationPoint();

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
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(location.getDirection()).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);
        if (isFirstLoc) {
            isFirstLoc = false;
            mCurrentLocaiton.setLatitude(location.getLatitude());
            mCurrentLocaiton.setLongitude(location.getLongitude());
            updateMapViewLocation(location.getLatitude(), location.getLongitude());
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
        return mCurrentLocaiton.getLatitude();
    }

    public double getCurLng() {
        return mCurrentLocaiton.getLongitude();
    }

}
