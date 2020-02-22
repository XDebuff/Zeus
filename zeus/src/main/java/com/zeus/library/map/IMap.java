package com.zeus.library.map;

import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;

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

    void addListener(ILocationListener locationListener);

    void removeListener(ILocationListener locationListener);

    void setListenerDelay(int time);

    Overlay addOverlay(OverlayOptions overlayOptions);
}
