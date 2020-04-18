package com.sysumach.machtv.live;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.BaseAdapter;

import com.sysumach.machtv.MyDecoration;
import com.sysumach.machtv.R;
import com.sysumach.machtv.adapter.LiveItemAdapter;
import com.sysumach.machtv.base.BaseActivity;

public class LiveActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void initView() {
        setSupportActionBar();
        setSupportArrowActionBar(true);

        mRecyclerView = bindView(R.id.live_rv);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setFocusable(false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new MyDecoration(this));

        LiveItemAdapter adapter = new LiveItemAdapter(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle("电视直播");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_live;
    }

    public static void launch(Activity activity){
        Intent intent = new Intent(activity, LiveActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }
}
