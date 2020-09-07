package com.debuff.zeus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;
import com.zeus.core.db.ZeusDatabase;

import java.sql.SQLException;

public abstract class DBDatabase extends ZeusDatabase {

    protected DBDatabase(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        super.onCreate(sqLiteDatabase, connectionSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        super.onUpgrade(sqLiteDatabase, connectionSource, oldVersion, newVersion);
    }

    @Override
    public abstract void createAllTable() throws SQLException;
}
