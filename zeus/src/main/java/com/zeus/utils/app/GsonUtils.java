package com.zeus.utils.app;

import com.google.gson.Gson;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class GsonUtils {

    private Gson mGson;

    private static GsonUtils GSON_UTILS;

    public static GsonUtils instance() {
        if (GSON_UTILS == null) {
            GSON_UTILS = new GsonUtils();
        }
        return GSON_UTILS;
    }

    public GsonUtils() {
        mGson = new Gson();
    }

    public <T> String toJson(T data) {
        return mGson.toJson(data);
    }
}
