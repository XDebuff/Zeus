package com.zeus.utils.app;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class LogUtils {

    public static void d(String tag, String content) {
        android.util.Log.d(tag, content);
    }

    public static void log(String tag, String info) {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        int i = 1;
        StackTraceElement s = ste[i];
        android.util.Log.d(tag, String.format("[%s]:[%s]-------:%s",
                s.getClassName(), s.getMethodName(),
                info));
    }

    public static void i(String tag, String content) {
        android.util.Log.i(tag, content);
    }

}
