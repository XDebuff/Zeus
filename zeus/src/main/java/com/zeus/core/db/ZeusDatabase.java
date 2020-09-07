package com.zeus.core.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zeus.core.cache.AppConstants;
import com.zeus.utils.app.LogUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***************************************************
 * Author: Debuff
 * Data: 2017/5/21
 * Description:
 ***************************************************/
public abstract class ZeusDatabase extends OrmLiteSqliteOpenHelper
        implements DatabaseManager.ICreateTable {

    private static final String TAG = "MCDatabaseHelper";

    protected static volatile ZeusDatabase mcDatabaseHelper;
    private Map<Class, Dao> daoCache = new HashMap<>();
    private List<Class> tableClasses = new ArrayList<>();

    protected ZeusDatabase(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    public void init() {

    }

    public static ZeusDatabase getInstance() {
        if (mcDatabaseHelper == null) {
            throw new NullPointerException("database isn't init");
        }
        return mcDatabaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        //创建表
        try {
            createAllTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        DBUpgradeInfo dbUpgradeInfo = new DBUpgradeInfo();
        dbUpgradeInfo.setOldVersion(oldVersion);
        dbUpgradeInfo.setNewVersion(newVersion);
        dbUpgradeInfo.setUpdateType(AppConstants.DB_UPGRADE_TYPE);
        DatabaseManager manager = new DatabaseManager(sqLiteDatabase, dbUpgradeInfo);
        try {
            manager.upgradeTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> Dao<T, Integer> getDaoImp(Class<T> clazz) {
        if (daoCache.get(clazz) == null) {
            try {
                daoCache.put(clazz, getDao(clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return daoCache.get(clazz);
    }
}
