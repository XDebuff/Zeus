package com.zeus.core.permission;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.zeus.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
@RuntimePermissions
public class PermissionActivity extends AppCompatActivity {

    private String TAG = "PermissionActivity";
    protected PermissionProcessor mPermissionProcessor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPermissionProcessor = new PermissionProcessor(this);
    }

    //--------------------------------------存储权限------------------------------------------------

    /**
     * Annotate a method which performs the action that requires one or more permissions
     */
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void needsExternalStoragePermission() {
        // NOTE: Perform action that requires the permission. If this is run by PermissionsDispatcher, the permission will have been granted
        Log.d(TAG, "needsExternalStoragePermission");
        Toast.makeText(this, "成功申请存储权限", Toast.LENGTH_SHORT).show();
    }


    /**
     * Annotate a method which is invoked if the user doesn't grant the permissions
     */
    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void onExternalStoragePermissionDenied() {
        Log.d(TAG, "onExternalStoragePermissionDenied");
        Toast.makeText(this, "用户拒绝了存储权限", Toast.LENGTH_SHORT).show();
    }

    /**
     * Annotate a method which explains why the permissions are needed.
     * It passes in a PermissionRequest object which can be used to continue
     * or abort the current permission request upon user input.
     * If you don't specify any argument for the method compiler will generate
     *
     * @param request
     */
    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void onShowRationaleForExternalStorage(PermissionRequest request) {
        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog.
        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
        Log.d(TAG, "onShowRationaleForExternalStorage");
        showHintDialog(R.string.permission_external_storage_rationale, request);
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void showNeverAskForExternalStorage() {
        Log.d(TAG, getResources().getString(R.string.permission_external_storage_rationale));
    }
    //----------------------------------------------------------------------------------------------


    //--------------------------------------定位权限------------------------------------------------

    /**
     * Annotate a method which performs the action that requires one or more permissions
     */
    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void needsLocationPermission() {
        // NOTE: Perform action that requires the permission. If this is run by PermissionsDispatcher, the permission will have been granted
        Log.d(TAG, "needsExternalStoragePermission");
        Toast.makeText(this, "我需要定位权限", Toast.LENGTH_SHORT).show();
    }


    /**
     * Annotate a method which is invoked if the user doesn't grant the permissions
     */
    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    public void onLocationPermissionDenied() {
        Log.d(TAG, "onExternalStoragePermissionDenied");
        Toast.makeText(this, "用户拒绝了定位权限", Toast.LENGTH_SHORT).show();
    }

    /**
     * Annotate a method which explains why the permissions are needed.
     * It passes in a PermissionRequest object which can be used to continue
     * or abort the current permission request upon user input.
     * If you don't specify any argument for the method compiler will generate
     *
     * @param request
     */
    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    public void onShowRationaleForLocation(PermissionRequest request) {
        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog.
        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
        Log.d(TAG, "onShowRationaleForExternalStorage");
        showHintDialog(R.string.permission_find_location_rationale, request);
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    public void showNeverAskForLocation() {
        Log.d(TAG, getResources().getString(R.string.permission_find_location_rationale));
        showAskAgainDialog("定位权限被拒绝，请手动打开权限");
    }
    //----------------------------------------------------------------------------------------------

    //--------------------------------------存储权限------------------------------------------------

    /**
     * Annotate a method which performs the action that requires one or more permissions
     */
    @NeedsPermission(Manifest.permission.CAMERA)
    public void needsCameraPermission() {
        // NOTE: Perform action that requires the permission. If this is run by PermissionsDispatcher, the permission will have been granted
        Log.d(TAG, "needsExternalStoragePermission");
        Toast.makeText(this, "成功申请相机权限", Toast.LENGTH_SHORT).show();
    }


    /**
     * Annotate a method which is invoked if the user doesn't grant the permissions
     */
    @OnPermissionDenied(Manifest.permission.CAMERA)
    public void onCameraPermissionDenied() {
        Log.d(TAG, "onExternalStoragePermissionDenied");
        Toast.makeText(this, "用户拒绝了存储权限", Toast.LENGTH_SHORT).show();
    }

    /**
     * Annotate a method which explains why the permissions are needed.
     * It passes in a PermissionRequest object which can be used to continue
     * or abort the current permission request upon user input.
     * If you don't specify any argument for the method compiler will generate
     *
     * @param request
     */
    @OnShowRationale(Manifest.permission.CAMERA)
    public void onShowRationaleForCamera(PermissionRequest request) {
        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog.
        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
        Log.d(TAG, "onShowRationaleForExternalStorage");
        showHintDialog(R.string.permission_external_storage_rationale, request);
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    public void showNeverAskForCamera() {
        Log.d(TAG, getResources().getString(R.string.permission_external_storage_rationale));
    }
    //----------------------------------------------------------------------------------------------


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult");
        PermissionActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    public void showAskAgainDialog(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
         startActivity(new Intent(Settings.ACTION_SETTINGS));
    }

    /**
     *
     * @param messageResId
     * @param request
     */
    protected void showHintDialog(int messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }
}
