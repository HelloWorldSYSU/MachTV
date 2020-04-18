package com.sysumach.machtv.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = getActivity().getLayoutInflater().inflate(getLayoutId(), container, false);
        initData();
        initView();
        return mContentView;
    }

    protected abstract int getLayoutId();
    protected abstract void initData();
    protected abstract void initView();

    protected <T extends View> T bindView(int resId){
        return (T) mContentView.findViewById(resId);
    }
}
