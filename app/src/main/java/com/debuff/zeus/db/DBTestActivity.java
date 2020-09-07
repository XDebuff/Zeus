package com.debuff.zeus.db;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.debuff.zeus.R;
import com.zeus.core.base.BaseActivity;
import com.zeus.core.db.ZeusDatabase;

class DBTestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_test_activity);
    }
}
