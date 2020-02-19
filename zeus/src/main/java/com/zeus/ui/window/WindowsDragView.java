package com.zeus.ui.window;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.WindowManager;

import com.zeus.ui.DragView;
import com.zeus.utils.app.LogUtils;

/***************************************************
 * Author: Debuff 
 * Data: 2019/3/31
 * Description: 
 ***************************************************/
public abstract class WindowsDragView extends DragView {

    private WindowManager mWindowManager;
    private ValueAnimator mValueAnimator;

    public WindowsDragView(Context context) {
        super(context);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mValueAnimator = ValueAnimator.ofFloat(1f, 0f);
        mValueAnimator.setDuration(2000);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                LogUtils.d(TAG, "value:" + value);
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) getLayoutParams();
                layoutParams.x *= value;
                mWindowManager.updateViewLayout(WindowsDragView.this, layoutParams);
                if (layoutParams.x == 0) {
                    mValueAnimator.cancel();
                }
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void updateLayout(int dx, int dy) {
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.getLayoutParams();
        layoutParams.x += dx;
        layoutParams.y += dy;
        mWindowManager.updateViewLayout(this, layoutParams);
    }

    @Override
    protected void finishDrag(int x, int y) {
        super.finishDrag(x, y);
        if (!mValueAnimator.isStarted()) {
            mValueAnimator.start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mValueAnimator.cancel();
        mValueAnimator = null;
    }
}
