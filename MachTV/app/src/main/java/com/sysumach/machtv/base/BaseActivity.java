package com.sysumach.machtv.base;

import android.os.Bundle;
import android.view.View;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.sysumach.machtv.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());

        initView();
        initData();
    }

    protected abstract void initView();
    protected abstract void initData();

    protected <T extends View> T bindView(int resId){
        return (T) findViewById(resId);
    }

    protected void setSupportActionBar(){
        mToolbar = bindView(R.id.toolbar);

        if(mToolbar != null){
            setSupportActionBar(mToolbar);
        }
    }

    protected void setActionBarIcon(int resId){
        if(mToolbar != null){
            mToolbar.setNavigationIcon(resId);
        }
    }

    protected void setTitle(String title){
        if(mToolbar != null){
            mToolbar.setTitle(title);
        }
    }

    protected void setSupportArrowActionBar(boolean isSupport){
        getSupportActionBar().setDisplayHomeAsUpEnabled(isSupport);
    }

    protected abstract int getlayoutId();
}
