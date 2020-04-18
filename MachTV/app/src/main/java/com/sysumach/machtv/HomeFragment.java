package com.sysumach.machtv;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.hejunlin.superindicatorlibray.CircleIndicator;
import com.hejunlin.superindicatorlibray.LoopViewPager;
import com.sysumach.machtv.base.BaseFragment;
import com.sysumach.machtv.live.LiveActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends BaseFragment {

    private LoopViewPager loopViewPager;
    private CircleIndicator circleIndicator;
    private GridView mGridView;
    private SimpleAdapter simpleAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        int icons[] = {
                R.drawable.gv_1, R.drawable.gv_2, R.drawable.gv_3,
                R.drawable.gv_4, R.drawable.gv_5, R.drawable.gv_6,
                R.drawable.gv_7, R.drawable.gv_8, R.drawable.gv_9
        };
        String[] from = {"imgId", "text"};
        int[] to = {R.id.gridview_img, R.id.gridview_text};
        String name[] = {"电视剧", "电影", "纪录片", "动漫", "音乐", "综艺", "直播", "我的收藏", "播放历史"};
        List dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("imgId", icons[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }

        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.gridview_item, from, to);
    }

    @Override
    protected void initView() {
        loopViewPager = bindView(R.id.loop_viewpager);
        circleIndicator = bindView(R.id.indicator);
        loopViewPager.setAdapter(new BasePicAdapter(getActivity()));
        loopViewPager.setLooperPic(true);
        circleIndicator.setViewPager(loopViewPager);

        mGridView = bindView(R.id.gv_channel);
        mGridView.setAdapter(simpleAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 6:
                        LiveActivity.launch(getActivity());
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    default:
                        break;
                        //DetailListActivity.LaunchDetailListActivity(getActivity(), position);
                }
            }
        });
    }


}
