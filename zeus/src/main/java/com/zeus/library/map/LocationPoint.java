package com.zeus.core.library.map;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class LocationPoint {

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private double latitude;

    private double longitude;
}
