package com.debuff.zeus;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.zeus.core.cache.SPUtils;
import com.zeus.utils.app.SDCardUtils;
import com.zeus.utils.java.DateUtils;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class LocationService extends IntentService {

    private String key = "LocationService";
    private int index;
    private boolean isStart;

    public static final String LOCATION_FILE_PATH = "lcoation-" + DateUtils.getDayByNumber() + ".txt";


    public LocationService() {
        super("location");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        isStart = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.zeus.location");
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, intentFilter);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        index = SPUtils.getGlobal().get(key, 0);
        while (isStart) {
            index++;
            try {
                Thread.sleep(1000);
                Log.d(key, "当前索引：" + index);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d(key, "当前索引：" + index);
        SPUtils.getGlobal().save(key, index);
        isStart = false;
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }


    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                switch (intent.getAction()) {
                    case "action.zeus.location":
                        String latlng = intent.getStringExtra("lat_lng");
                        Log.d(key, latlng);
                        SDCardUtils.save(LOCATION_FILE_PATH, latlng, true);
                        break;
                    default:
                        break;
                }
            }
        }
    };

}
