package com.sysumach.machtv;

import androidx.fragment.app.Fragment;

import java.util.HashMap;

public class FragmentManagerWrapper {

    private volatile static FragmentManagerWrapper mInstance = null;

    public static FragmentManagerWrapper getInstance(){
        if(mInstance == null){
            synchronized (FragmentManagerWrapper.class){
                if(mInstance == null){
                    mInstance = new FragmentManagerWrapper();
                }
            }
;        }
        return mInstance;
    }

    private HashMap<String, Fragment> mHashmap = new HashMap<>();

    public Fragment createFragment(Class<?> clazz, boolean ifObtain){
        Fragment mBaseFragment = null;
        String className = clazz.getName();

        if(!mHashmap.containsKey(className)){
            try {
                mBaseFragment = (Fragment)Class.forName(className).newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            mBaseFragment = mHashmap.get(className);
        }

        if(ifObtain){
            mHashmap.put(className, mBaseFragment);
        }

        return mBaseFragment;
    }
}
