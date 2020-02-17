package com.zeus.core.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/***************************************************
 * Author: Debuff
 * Data: 2017/5/21
 * Description:
 ***************************************************/

public class SPUtils {

    private static Context mContext;
    private static SharedPreferencesMethod mGlobalSharedPreferences;
    private static SharedPreferencesMethod mUserSharedPreferences;
    private static SharedPreferencesMethod mDefaultSharedPreferences;

    /**
     * application 初始化
     *
     * @param context
     */
    public static void init(Context context) {
        mContext = context.getApplicationContext();
        mDefaultSharedPreferences = new SharedPreferencesMethod(PreferenceManager.getDefaultSharedPreferences(context));
        mGlobalSharedPreferences = new SharedPreferencesMethod(context.getApplicationContext().getSharedPreferences(SPConstants.APP_GLOBAL_SP, Context.MODE_PRIVATE));
    }

    public void save(String key, String value) {

    }


    public static SharedPreferencesMethod getUser() {
        //判断用户是否登录
//        if (FictitiousApplication.getUser() == null) {
//            throw new IllegalArgumentException("must login...");
//        }
//        //初始化
//        if (mUserSharedPreferences == null) {
//            mUserSharedPreferences = new SharedPreferencesMethod(mContext.getApplicationContext().getSharedPreferences(SPConstants.APP_USER_SP + "_" + FictitiousApplication.getUser().getUid(), Context.MODE_PRIVATE));
//        }
        return mUserSharedPreferences;
    }

    public static SharedPreferencesMethod getGlobal() {
        return mGlobalSharedPreferences;
    }

    public static SharedPreferencesMethod getDefault() {
        return mDefaultSharedPreferences;
    }

    public static class SharedPreferencesMethod {

        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor mEditor;

        public SharedPreferencesMethod(SharedPreferences sharedPreferences) {
            super();
            this.sharedPreferences = sharedPreferences;
        }

        public void save(String key, String value) {
            sharedPreferences.edit().putString(key, value).apply();
        }

        public void save(String key, Integer value) {
            sharedPreferences.edit().putInt(key, value).apply();
        }

        public void save(String key, Boolean value) {
            sharedPreferences.edit().putBoolean(key, value).apply();
        }

        public void save(String key, Long value) {
            sharedPreferences.edit().putLong(key, value).apply();
        }

        public Integer get(String key, Integer defaultValue) {
            return sharedPreferences.getInt(key, defaultValue);
        }

        public String get(String key, String defaultValue) {
            return sharedPreferences.getString(key, defaultValue);
        }

        public Boolean get(String key, Boolean defaultValue) {
            return sharedPreferences.getBoolean(key, defaultValue);
        }

        public Long get(String key, Long defaultValue) {
            return sharedPreferences.getLong(key, defaultValue);
        }

        public void clear() {
            sharedPreferences.edit().clear().apply();
        }


        public void remove(String key) {
            sharedPreferences.edit().remove(key).apply();
        }


    }

}
