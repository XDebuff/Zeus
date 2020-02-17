package com.zeus.core.cache;

import android.content.Context;
import android.util.DisplayMetrics;

/***************************************************
 * Author: Debuff
 * Data: 2019/7/15
 * Description:App所需的基本配置
 ***************************************************/
public class AppConfig {

    private int mScreenWidth;
    private int mScreenHeight;

    private static Context mContext;
    private static String mCacheDir;
    private static AppConfig mAppConfig;

    public static String DEFAULT_KEY_NAME = "zeus_key";

    private AppConfig() {
        super();
    }

    public static AppConfig getInstance() {
        if (mAppConfig == null) {
            throw new IllegalArgumentException("必须在application中初始化");
        }
        return mAppConfig;
    }

    public static void init(Context context, String cacheDir) {
        mContext = context;
        mCacheDir = cacheDir;
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

    public String getCacheDir() {
        return mCacheDir;
    }


}
