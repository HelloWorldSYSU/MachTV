package com.sysumach.machtv;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.sysumach.machtv.base.BaseActivity;
import com.sysumach.machtv.model.Channel;

import java.util.HashMap;

public class DetailListActivity extends BaseActivity {

    private static final String CHANNEL_ID = "channel_id";
    private int ChannelIdInt = 0;
    private int SiteIdInt = 0;
    private String ChannelName = null;
    private ViewPager viewPager;

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(ChannelName);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if(intent != null){
            ChannelIdInt = intent.getIntExtra(CHANNEL_ID, 0);
        }
        Channel channel = new Channel();
        ChannelName = channel.getName(ChannelIdInt);

        setSupportActionBar();
        setSupportArrowActionBar(true);

        viewPager = bindView(R.id.pager_detai_list);
        viewPager.setAdapter(new SitePagerAdapter(getSupportFragmentManager(), this, SiteIdInt, ChannelIdInt));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_detail_list;
    }

    public static void LaunchDetailListActivity(Context context, int channel_id){
        Intent intent = new Intent(context, DetailListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(CHANNEL_ID, channel_id);
        context.startActivity(intent);
    }

    private class SitePagerAdapter extends FragmentPagerAdapter{

        private Context mContext;
        private int Channel_id;
        private int Site_id;
        private HashMap<Integer, DetailListFragment> mHashMapPager = new HashMap<>();

        public SitePagerAdapter(@NonNull FragmentManager fm, Context context, int siteId, int channel_id) {
            super(fm);
            mContext = context;
            Site_id = siteId;

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = DetailListFragment.newInstance(Site_id, Channel_id);
            return fragment;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Object object = super.instantiateItem(container, position);

            if(object instanceof DetailListFragment){
                mHashMapPager.put(position, (DetailListFragment)object);
            }

            return object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
            mHashMapPager.remove(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
