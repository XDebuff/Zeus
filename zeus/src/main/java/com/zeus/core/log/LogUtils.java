package com.zeus.core.log;

import android.util.Log;

class LogUtils {

    private static boolean mIsDebug;

    public static void setDebug(boolean isDebug) {
        mIsDebug = isDebug;
    }

    public static void d(String tag, String content) {
        Log.d(tag, content);
    }
}
