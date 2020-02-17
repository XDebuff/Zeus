package com.zeus.core.permission;

import permissions.dispatcher.PermissionRequest;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class LocationPermission extends BasePermission {

    public LocationPermission(PermissionActivity activity) {
        super(activity);
    }

    @Override
    public void requestPermission() {
        // NOTE: Perform action that requires the permission. If this is run by PermissionsDispatcher, the permission will have been granted
        PermissionActivityPermissionsDispatcher.needsLocationPermissionWithPermissionCheck(mActivity);
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
