package com.zeus.core.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 数据库表添加，数据库升级
 ***************************************************/
public class DatabaseManager {

    private final int mOldVersion;
    private final int mNewVersion;
    private final SQLiteDatabase mSqLiteDatabase;
    private List<String> passTables = Arrays.asList(new String[]{"sqlite_sequence", "android_metadata"});

    public void setICreateTable(ICreateTable ICreateTable) {
        mICreateTable = ICreateTable;
    }

    private ICreateTable mICreateTable;

    public DatabaseManager(SQLiteDatabase sqLiteDatabase, DBUpgradeInfo dbUpgradeInfo) {
        super();
        mSqLiteDatabase = sqLiteDatabase;
        mOldVersion = dbUpgradeInfo.getOldVersion();
        mNewVersion = dbUpgradeInfo.getNewVersion();
    }

    public void upgradeTable() throws SQLException {
        //todo 应该是事务操作
        //获取所有表
        List<TableSet> tableSets = getAllTables();
        //更名旧表
        renameTables(tableSets);
        //创建新表
        createTables();
        //将旧表数据拷贝至新表
        moveData(tableSets);
        //删除现有表
        dropTable(tableSets);
        //拷贝临时表
    }

    private void dropTable(List<TableSet> tableSets) {
        for (TableSet table : tableSets) {
            String sql = "DROP TABLE " + table.getTempTableName();
            mSqLiteDatabase.execSQL(sql);
        }
    }

    private void moveData(List<TableSet> tableSets) {
        for (TableSet table : tableSets) {
            StringBuilder columnsStr = new StringBuilder();
            String oldTableName = table.getTempTableName();
            String newTableName = table.getTableName();
            //旧字段
            Map<String, Integer> newColumn = new HashMap<>();
            Cursor columnCursor = mSqLiteDatabase.rawQuery("PRAGMA table_info(" + oldTableName + ")", null);
            Cursor newColumnCursor = mSqLiteDatabase.rawQuery("PRAGMA table_info(" + newTableName + ")", null);
            //新的列
            if (newColumnCursor != null && newColumnCursor.moveToFirst()) {
                do {
                    newColumn.put(newColumnCursor.getString(1), 1);
                } while (newColumnCursor.moveToNext());
            }
            if (columnCursor != null && columnCursor.moveToFirst()) {
                do {
                    if (newColumn.containsKey(columnCursor.getString(1))) {
                        columnsStr.append(columnCursor.getString(1)).append(",");
                    }
                } while (columnCursor.moveToNext());
            }
            columnsStr.delete(Math.max(0, columnsStr.length() - 1), columnsStr.length());
            if (columnCursor != null) {
                columnCursor.close();
            }
            if (newColumnCursor != null) {
                newColumnCursor.close();
            }
            Log.d("upgradeTables", columnsStr.toString());
            if (!TextUtils.isEmpty(columnsStr)) {
                String sql = "INSERT INTO " + newTableName +
                        " (" + columnsStr + ") " +
                        " SELECT " + columnsStr + " FROM " + oldTableName;
                mSqLiteDatabase.execSQL(sql);
            }
        }
    }

    private void renameTables(List<TableSet> tableSets) {
        for (TableSet tableSet : tableSets) {
            mSqLiteDatabase.execSQL("ALTER TABLE " + tableSet.getTableName()
                    + " RENAME TO " + tableSet.getTempTableName());
        }
    }

    public List<TableSet> getAllTables() {
        List<TableSet> allTables = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.rawQuery("select name from sqlite_master " +
                "where type='table' order by name", null);
        while (cursor.moveToNext()) {
            String tableName = cursor.getString(0);
            if (!passTables.contains(tableName)) {
                allTables.add(new TableSet(tableName));
            }
        }
        return allTables;
    }

    private void createTables() throws SQLException {
        if (mICreateTable == null) {
            throw new IllegalArgumentException("接口没有实现");
        }
        mICreateTable.createAllTable();
    }

    class TableSet {
        public String getTableName() {
            return tableName;
        }

        private String tableName;

        public String getTempTableName() {
            return tempTableName;
        }

        private String tempTableName;

        public TableSet(String tableName) {
            this.tableName = tableName;
            this.tempTableName = "temp_" + tableName;
        }
    }

    public interface ICreateTable {
        void createAllTable() throws SQLException;
    }

}


