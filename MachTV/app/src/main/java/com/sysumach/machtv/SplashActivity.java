package com.sysumach.machtv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    private final static int GO_HOME = 1;
    private final static int GO_GUIDE = 2;
    private static int ENTER_DURATION = 0;
    private SharedPreferences mSharedPreference;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what){
                case GO_GUIDE:
                    startGuideActivity();
                    break;
                case GO_HOME:
                    startHomeActivity();
                    break;
                default:
                    break;
            }

        }
    };

    private void startGuideActivity() {
        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private void startHomeActivity(){
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreference = getSharedPreferences("config", MODE_PRIVATE);
        init();
    }

    private void init() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            View view = window.getDecorView();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        boolean isFirst = mSharedPreference.getBoolean("mIsFirstIn", true);
        if(isFirst){
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, ENTER_DURATION);
        }else{
            mHandler.sendEmptyMessageDelayed(GO_HOME, ENTER_DURATION);
        }
    }
}
