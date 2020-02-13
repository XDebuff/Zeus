package com.zeus.core.base;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.zeus.R;

import androidx.annotation.DrawableRes;
import androidx.annotation.MenuRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

/***************************************************
 * Author: Debuff 
 * Data: 2019/12/9
 * Description: 
 ***************************************************/
public abstract class ToolBarActivity extends BaseActivity {

    protected Toolbar mToolbar;
    private ToolbarBuilder mToolbarBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(mToolbarBuilder.menuRes, menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    protected void initToolbar() {
        View toolbarRoot = LayoutInflater.from(this).inflate(R.layout.app_toolbar, null, false);
        mToolbar = toolbarRoot.findViewById(R.id.app_toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        initToolbarAttr();
        mToolbarContainer.setVisibility(View.VISIBLE);
        mToolbarContainer.addView(toolbarRoot);
    }

    private void initToolbarAttr() {
        mToolbarBuilder = initToolbarBuilder();
        mToolbarBuilder.setToolbar(mToolbar);
        mToolbarBuilder.build();
    }

    protected abstract ToolbarBuilder initToolbarBuilder();


    public class ToolbarBuilder {

        private String title;

        @DrawableRes
        private int logo = -1;

        @MenuRes
        private int menuRes = -1;

        private Toolbar mToolbar;

        public void setToolbar(Toolbar toolbar) {
            mToolbar = toolbar;
        }

        public ToolbarBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ToolbarBuilder logo(int logo) {
            this.logo = logo;
            return this;
        }

        public ToolbarBuilder menuRes(int menuRes) {
            this.menuRes = menuRes;
            return this;
        }

        public void build() {
            if (!TextUtils.isEmpty(title)) {
                mToolbar.setTitle(title);
            }
            if (logo != -1) {
                mToolbar.setLogo(logo);
            }
            if (menuRes != -1) {
                mToolbar.inflateMenu(menuRes);
            }
            setSupportActionBar(mToolbar);
        }
    }

}
