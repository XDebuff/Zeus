package com.zeus.cache;

import android.content.Context;
import android.util.DisplayMetrics;

/***************************************************
 * Author: Debuff
 * Data: 2019/7/15
 * Description:
 ***************************************************/
public class AppConfig {

    private int mScreenWidth;
    private static Context mContext;
    private static AppConfig mAppConfig;

    private AppConfig() {
        super();
    }

    public static AppConfig getInstance() {
        if (mAppConfig == null) {
            mAppConfig = new AppConfig();
        }
        return mAppConfig;
    }

    public static void init(Context context) {
        mContext = context;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mAppConfig = new AppConfig();
        mAppConfig.setScreenHeight(displayMetrics.heightPixels);
        mAppConfig.setScreenWidth(displayMetrics.widthPixels);
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        mScreenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        mScreenHeight = screenHeight;
    }

    private int mScreenHeight;

}
