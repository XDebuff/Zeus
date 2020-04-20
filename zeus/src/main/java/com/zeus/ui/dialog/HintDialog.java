package com.zeus.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;

public class HintDialog {
    private AlertDialog mAlertDialog;


    public HintDialog(Context context) {
        super();
        mAlertDialog = new AlertDialog.Builder(context).create();
    }

    public void show(String content) {
        mAlertDialog.setMessage(content);
        mAlertDialog.show();
    }
}
