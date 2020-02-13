package com.zeus.core.permission;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/***************************************************
 * Author: Debuff 
 * Data: 2019/3/10
 * Description: 权限处理器
 ***************************************************/
public class PermissionProcessor {

    private Activity mActivity;

    public PermissionProcessor(Activity activity) {
        super();
        mActivity = activity;
    }

    public boolean isPermission(String[] permissions) {
        //6.0以前不进行检测
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        return PackageManager.PERMISSION_GRANTED ==
                ContextCompat.checkSelfPermission(mActivity.getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public void requestPermission(String[] permissions) {
        if (!isPermission(permissions)) {
            ActivityCompat.requestPermissions(mActivity,
                    permissions,
                    1);
        }
    }

    public void requestCallback(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else { //用户不同意，向用户展示该权限作用
                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    new AlertDialog.Builder(mActivity).setMessage("权限申请")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions(mActivity,
                                            permissions, 0);
                                }
                            })
                            .setNegativeButton("Cancel", null).create().show();
                }
            }
        }
    }

}
