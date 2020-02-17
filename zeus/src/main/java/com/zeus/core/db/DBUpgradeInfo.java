package com.zeus.core.db;

/***************************************************
 * Author: Debuff 
 * Data: 2018/12/11
 * Description: 
 ***************************************************/
public class DBUpgradeInfo {

    public static int ALL = -1;

    public static int CREATE = 1;

    public static int UPDATE = 2;

    public static int DELETE = 3;

    private int mNewVersion;

    public void setNewVersion(int newVersion) {
        mNewVersion = newVersion;
    }

    public void setOldVersion(int oldVersion) {
        mOldVersion = oldVersion;
    }

    public int getOldVersion() {
        return mOldVersion;
    }

    private int mOldVersion;

    public int getNewVersion() {
        return mNewVersion;
    }

    public int getUpdateType() {
        return updateType;
    }

    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    private int updateType;
}
