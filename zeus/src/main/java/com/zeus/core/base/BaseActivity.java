package com.zeus.core.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.zeus.R;
import com.zeus.core.permission.PermissionActivity;
import com.zeus.core.permission.PermissionProcessor;
import com.zeus.core.ui.IHintView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public abstract class BaseActivity extends PermissionActivity implements IHintView {

    protected final String TAG = this.getClass().getSimpleName();

    private View emptyView;
    private View loadingView;
    protected FrameLayout mToolbarContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_container);
        View childRootView = LayoutInflater.from(this).inflate(layoutResID, null, false);
        FrameLayout root = findViewById(R.id.view_container);
        root.addView(childRootView);
        emptyView = findViewById(R.id.view_empty);
        loadingView = findViewById(R.id.view_loading);
        mToolbarContainer = findViewById(R.id.app_toolbar_container);
        initToolbar();
        hiddenEmptyView();
        hiddenLoadingView();
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingView() {
        emptyView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hiddenLoadingView() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void hiddenEmptyView() {
        emptyView.setVisibility(View.GONE);
    }

    protected void initToolbar() {

    }

}
