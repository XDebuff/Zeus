package com.zeus.core.base;

import com.facebook.stetho.Stetho;
import com.zeus.cache.AppConfig;
import com.zeus.cache.SPUtils;

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
        AppConfig.init(getApplicationContext());
    }
}
