package com.debuff.zeus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zeus.core.biometricPrompt.BiometricPromptManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView mBaiduMapView;
    TextView mStartBiometricPrompt;
    private BiometricPromptManager mBiometricPromptManager;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBaiduMapView = findViewById(R.id.baidu_map_view);
        mStartBiometricPrompt = findViewById(R.id.start_biometric_prompt);
        mBiometricPromptManager = new BiometricPromptManager(MainActivity.this);
        mStartBiometricPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBiometricPromptManager.authenticate();
            }
        });
        mBaiduMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBiometricPromptManager.onPause();
    }
}
