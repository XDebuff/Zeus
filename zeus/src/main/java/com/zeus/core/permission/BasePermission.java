package com.zeus.core.permission;

import permissions.dispatcher.PermissionRequest;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public abstract class BasePermission implements IPermission {

    protected PermissionActivity mActivity;

    public BasePermission(PermissionActivity activity) {
        super();
        mActivity = activity;
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
