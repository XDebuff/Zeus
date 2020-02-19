package com.zeus.ui.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

/***************************************************
 * Author: Debuff 
 * Data: 2019/3/10
 * Description: 
 ***************************************************/
public class BaseWindowManager {

    private final Context mContext;
    private final WindowManager mWindowManager;
    private View mMainView;

    public BaseWindowManager(Context context) {
        mContext = context;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    private static final WindowManager.LayoutParams LAYOUT_PARAMS;

    static {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.x = 0;
        params.y = 0;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.LEFT | Gravity.TOP;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        LAYOUT_PARAMS = params;
    }

    public void addView(View view) {
        mMainView = view;
        mMainView.setLayoutParams(LAYOUT_PARAMS);
        mWindowManager.addView(mMainView, LAYOUT_PARAMS);
    }

    public void removeView() {
        mWindowManager.removeView(mMainView);
    }

    public void updateViewLayout(View view, WindowManager.LayoutParams layoutParam) {
        mWindowManager.updateViewLayout(view,layoutParam);
    }
}

