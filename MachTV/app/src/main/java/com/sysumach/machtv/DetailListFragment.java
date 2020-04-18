package com.sysumach.machtv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sysumach.machtv.base.BaseFragment;
import com.sysumach.machtv.model.Site;
import com.sysumach.machtv.widget.PullLoadRecyclerView;

import org.w3c.dom.Text;

public class DetailListFragment extends BaseFragment {

    private static int mSiteId, mChannelId;
    private PullLoadRecyclerView pullLoadRecyclerView;
    private TextView tv_err;
    private DataListAdapter dataListAdapter;
    private int column = 0;

    public DetailListFragment(){
    }


    public DetailListFragment(int siteId, int channId){
        mSiteId = siteId;
        mChannelId = channId;
    }

    public static Fragment newInstance(int siteId, int channId){
        Fragment fragment = new DetailListFragment();
        mChannelId = channId;
        mSiteId = siteId;
        Bundle bundle = new Bundle();
        bundle.putInt("Channel", channId);
        bundle.putInt("Site", siteId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.detail_fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mSiteId == Site.LETV){
            column = 2;
        }else{
            column = 3;
        }
        dataListAdapter.setColumn(column);
    }

    @Override
    protected void initView() {
        tv_err = bindView(R.id.tv_neterror_tip);
        tv_err.setText(getActivity().getResources().getString(R.string.loading_tip));
        pullLoadRecyclerView = bindView(R.id.plrv_list);
        pullLoadRecyclerView.setGridLayout(3);
        pullLoadRecyclerView.setAdapter(new DataListAdapter());
        pullLoadRecyclerView.setOnPullLoadMoreListener(new OnPullLoadMoreListerner());

    }

    class DataListAdapter extends RecyclerView.Adapter{

        public void setColumn(int column){

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class OnPullLoadMoreListerner implements PullLoadRecyclerView.OnPullLoadListener{

        @Override
        public void reFresh() {

        }

        @Override
        public void loadMore() {

        }
    }
}
