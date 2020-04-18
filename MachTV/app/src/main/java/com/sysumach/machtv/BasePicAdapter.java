package com.sysumach.machtv;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class BasePicAdapter extends PagerAdapter {

    private Context mContext;
    private int[] mDes = new int[]{
            R.string.a_name,
            R.string.b_name,
            R.string.c_name,
            R.string.d_name,
            R.string.e_name
    };

    private int[] mImg = new int[]{
            R.drawable.a_pic,
            R.drawable.b_pic,
            R.drawable.c_pic,
            R.drawable.d_pic,
            R.drawable.e_pic
    };

    public BasePicAdapter(Activity activity){
        mContext = activity;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_pic_item, null);
        TextView mTextView = view.findViewById(R.id.tv_dec);
        ImageView mImageView = view.findViewById(R.id.iv_img);
        mTextView.setText(mDes[position]);
        mImageView.setImageResource(mImg[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
