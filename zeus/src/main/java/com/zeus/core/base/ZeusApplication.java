package com.zeus.core.base;

//import com.baidu.mapapi.CoordType;
//import com.baidu.mapapi.SDKInitializer;
//import com.baidu.mapapi.CoordType;
//import com.baidu.mapapi.SDKInitializer;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.stetho.Stetho;
import com.zeus.core.cache.AppConfig;
import com.zeus.core.cache.SPUtils;

import androidx.multidex.MultiDexApplication;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class ZeusApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        SPUtils.init(this);
        AppConfig.init(getApplicationContext(), "zeus");
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
}
