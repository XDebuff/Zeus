package com.zeus.library.map;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public interface IMap {
    void start();

    void location();

    LocationPoint getCurLatLng();

    void onResume();

    void onPause();

    void onDestroy();
}
