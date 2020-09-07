package com.zeus.core.permission;

import permissions.dispatcher.PermissionRequest;

/***************************************************
 * Author: Debuff
 * Data: 2017/5/21
 * Description:
 ***************************************************/
public class CameraPermission extends BasePermission {

    public CameraPermission(PermissionActivity activity) {
        super(activity);
    }

    @Override
    public void requestPermission() {
        PermissionActivityPermissionsDispatcher.needsCameraPermissionWithPermissionCheck(mActivity);
    }

    @Override
    public void needsPermission() {

    }

    @Override
    public void onPermissionDenied() {

    }

    @Override
    public void onShowRationale(PermissionRequest request) {

    }

    @Override
    public void showHintDialog(int messageResId, PermissionRequest request) {

    }


}
