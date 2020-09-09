package com.zeus.core.permission;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/***************************************************
 * Author: Debuff 
 * Data: 2019/3/10
 * Description: 权限处理器
 ***************************************************/
public class PermissionProcessor {

    private PermissionActivity mActivity;

    private Map<String, IPermission> mPermissions = new HashMap<>();

    public PermissionProcessor(PermissionActivity activity) {
        super();
        mActivity = activity;
        init();
    }

    private void init() {
        register(Manifest.permission.WRITE_EXTERNAL_STORAGE, new ExternalStoragePermission(mActivity));
        register(Manifest.permission.ACCESS_FINE_LOCATION, new LocationPermission(mActivity));
        register(Manifest.permission.ACCESS_FINE_LOCATION, new LocationPermission(mActivity));
    }

    public void register(String permissionStr, IPermission permission) {
        mPermissions.put(permissionStr, permission);
    }

    public void checkPermission(String... permissions) {
        //6.0以前不进行检测
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        for (String permissionKey : permissions) {
            IPermission permission = mPermissions.get(permissionKey);
            if (permission != null) {
                permission.requestPermission();
            }
        }
    }

    public void checkPermission(String permission) {
        checkPermission(new String[]{permission});
    }

}
