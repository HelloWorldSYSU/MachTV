package com.sysumach.machtv.model;

public class Channel{

    public String[] ChannelNames = {"电视剧", "电影", "纪录片", "动漫", "音乐", "综艺", "直播", "我的收藏", "播放历史"};

    public String getName(int position){
        return ChannelNames[position];
    }


}
