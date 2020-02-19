package com.zeus.ui;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.zeus.utils.app.LogUtils;

/***************************************************
 * Author: Debuff
 * Data: 2019/3/10
 * Description:
 ***************************************************/
public abstract class DragView extends FrameLayout {
    public static final String TAG = "FloatingView";

    protected final Context mContext;
    Point preP, curP;

    public DragView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    protected abstract void initView();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preP = new Point((int) event.getRawX(), (int) event.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                curP = new Point((int) event.getRawX(), (int) event.getRawY());
                int dx = curP.x - preP.x;
                int dy = curP.y - preP.y;
                LogUtils.d(TAG, curP.toString());
                updateLayout(dx, dy);
                preP = curP;
                break;
            case MotionEvent.ACTION_UP:
                finishDrag(getTop(), getLeft());
                preP = curP;
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    protected void finishDrag(int x, int y) {
        LogUtils.d(TAG, "x:" + x + "\r" + "y:" + y);
    }

    protected void updateLayout(int dx, int dy) {
        layout(getLeft() + dx, getTop() + dy, getLeft() + getWidth(), getTop() + getHeight());
    }
}
