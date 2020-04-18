package com.sysumach.machtv.model;

public class Site {

    public static final int LETV = 1;
    public static final int SOHU = 2;

    public static final String case1 = "乐视视频";
    public static final String case2 = "搜狐视频";

    public static String getName(int id){
        if(id == 1){
            return case1;
        }else{
            return case2;
        }
    }

}
