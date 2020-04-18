package com.sysumach.machtv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import com.sysumach.machtv.base.BaseActivity;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PlayActivity extends BaseActivity {

    private String mVideoPath;
    private VideoView mVideoView;
    private static final String ADDR = "stream_addr";

    @Override
    protected void initView() {
        mVideoPath = getIntent().getStringExtra(ADDR);
        Log.d("das", mVideoPath);
        mVideoView = bindView(R.id.video_view);
// init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        if (mVideoPath != null) {
            Log.d("das", mVideoPath);
            mVideoView.setVideoPath(mVideoPath);
        }
        mVideoView.start();
    }

    public static void luanchActivity(Context context, String url){
        Intent intent = new Intent(context, PlayActivity.class);
        intent.putExtra(ADDR, url);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_play;
    }
}
