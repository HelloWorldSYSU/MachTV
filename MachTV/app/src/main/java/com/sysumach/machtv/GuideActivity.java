package com.sysumach.machtv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener{

    private List<View> viewArrayListist = new ArrayList<>();
    private ViewPager mViewPager;
    private ImageView[] imageViews;
    private LinearLayout linearLayout;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initViewPager();
        initDots();
    }

    private void initDots() {
        LinearLayout dotslayout = findViewById(R.id.ll_dots_layout);
        imageViews = new ImageView[viewArrayListist.size()];
        for(int i = 0; i < viewArrayListist.size(); i ++){
            imageViews[i] = (ImageView) dotslayout.getChildAt(i);
            imageViews[i].setEnabled(false);
        }
        imageViews[0].setEnabled(true);
    }

    private void setCurrentDotPosition(int position){
        for(int i = 0; i < imageViews.length; i ++){
            if(i == position){
                imageViews[position].setEnabled(true);
            }else{
                imageViews[i].setEnabled(false);
            }
        }
    }

    private void initViewPager() {
        mViewPager = findViewById(R.id.viewpager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(viewArrayListist, this);
        mViewPager.setAdapter(myPagerAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    private void initView(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            View view = window.getDecorView();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        viewArrayListist.add(layoutInflater.inflate(R.layout.guide_one_layout, null));
        viewArrayListist.add(layoutInflater.inflate(R.layout.guide_two_layout, null));
        viewArrayListist.add(layoutInflater.inflate(R.layout.guide_there_layout, null));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentDotPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyPagerAdapter extends PagerAdapter{

        private List<View> mImageViewList;
        private Context mContext;

        MyPagerAdapter(List<View> viewList, Context context){
            super();
            mContext = context;
            mImageViewList = viewList;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            if(mImageViewList == null || mImageViewList.size() == 0) return null;
            container.addView(mImageViewList.get(position));
            if(position == mImageViewList.size() - 1){
                ImageView imageView = mImageViewList.get(position).findViewById(R.id.iv_enter_home);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GuideActivity.this, HomeActivity.class);
                        startActivity(intent);

                        SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putBoolean("mIsFirstIn", false);
                        edit.commit();

                        finish();
                    }
                });
            }
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            if(mImageViewList == null || mImageViewList.size() == 0) return;
            container.removeView(mImageViewList.get(position));
        }

        @Override
        public int getCount() {
            if(mImageViewList != null){
                return mImageViewList.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
