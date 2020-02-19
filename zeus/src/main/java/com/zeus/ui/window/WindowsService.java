package com.zeus.ui.window;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/***************************************************
 * Author: Debuff 
 * Data: 2019/3/10
 * Description: 悬浮窗
 ***************************************************/
public class WindowsService extends Service {

    private BaseWindowManager mMCWindowManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mMCWindowManager == null) {
            mMCWindowManager = new BaseWindowManager(this);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
