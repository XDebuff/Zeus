package com.zeus.core.permission;

import androidx.annotation.StringRes;
import permissions.dispatcher.PermissionRequest;

/***************************************************
 * Author: Debuff 
 * Data: 2019/3/10
 * Description: 
 ***************************************************/
public interface IPermission {

    void requestPermission();

    void needsPermission();

    void onPermissionDenied();

    void onShowRationale(PermissionRequest request);

    void showHintDialog(@StringRes int messageResId, final PermissionRequest request);

}
