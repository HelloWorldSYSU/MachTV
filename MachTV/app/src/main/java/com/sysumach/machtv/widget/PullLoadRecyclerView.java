package com.sysumach.machtv.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sysumach.machtv.R;

public class PullLoadRecyclerView extends LinearLayout {

    private Animation animation;
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView recyclerView;
    private ImageView imageView;
    private TextView textView;
    private boolean mIsRefresh = false;
    private boolean mIsLoadingMore = false;
    private View mFootView;
    private OnPullLoadListener mOnPullLoadListener;

    public PullLoadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PullLoadRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public PullLoadRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    @SuppressLint("ResourceAsColor")
    private void initView(Context context){
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.pull_loadmore, null);
        mSwipeRefresh = view.findViewById(R.id.sfl_list);
        mSwipeRefresh.setColorSchemeColors(android.R.color.holo_green_dark, android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshOnRefresh());

        recyclerView = view.findViewById(R.id.rev_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return !mIsRefresh && !mIsLoadingMore;
            }
        });
        recyclerView.setVerticalScrollBarEnabled(false);
        recyclerView.addOnScrollListener(new RecylerViewOnScroll());

        mFootView = view.findViewById(R.id.footer_view);
        imageView = mFootView.findViewById(R.id.iv_footer);
        animation = AnimationUtils.loadAnimation(mContext, R.anim.loading_anim);
        textView = mFootView.findViewById(R.id.tv_footer);
        mFootView.setVisibility(View.GONE);
        this.addView(view);
    }

    public void setGridLayout(int spanCount){
        GridLayoutManager manager = new GridLayoutManager(mContext, spanCount);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        if(adapter != null){
            recyclerView.setAdapter(adapter);
        }
    }

    class SwipeRefreshOnRefresh implements SwipeRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh() {
            if(!mIsRefresh){
                mIsRefresh = true;
                refreshData();
            }
        }
    }

    class RecylerViewOnScroll extends RecyclerView.OnScrollListener{
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int firstItem = 0, lastItem = 0, totalCount = 0;
            RecyclerView.LayoutManager manager= recyclerView.getLayoutManager();
            totalCount = manager.getItemCount();
            if(manager instanceof GridLayoutManager){
                GridLayoutManager gridLayoutManager = (GridLayoutManager)manager;
                firstItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
                lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();

                if(firstItem == 0 || firstItem == RecyclerView.NO_POSITION){
                    lastItem = gridLayoutManager.findLastVisibleItemPosition();
                }
            }

            if(mSwipeRefresh.isEnabled()){
                mSwipeRefresh.setEnabled(true);
            }else{
                mSwipeRefresh.setEnabled(false);
            }

            if(!mIsRefresh && !mIsLoadingMore && mSwipeRefresh.isEnabled()
                    && totalCount == lastItem && (dx > 0 || dy > 0)){
                loadMoreData();
                mIsLoadingMore = true;
            }
        }
    }

    private void loadMoreData(){
        if(mOnPullLoadListener != null){
            mOnPullLoadListener.loadMore();
            mFootView.setVisibility(View.VISIBLE);
            animation.start();
            mOnPullLoadListener.loadMore();
        }
    }

    public void setRefreshCompleted(){
        mIsRefresh = false;
        setRefreshing(false);
    }

    private void setRefreshing(final boolean isRefreshing){
        mSwipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefresh.setRefreshing(isRefreshing);
            }
        });
    }

    public void setLoadMoreCompleted(){
        mIsLoadingMore = false;
        mIsRefresh = false;
        setRefreshing(false);
    }

    private void refreshData(){
        if(mOnPullLoadListener != null){
            mOnPullLoadListener.reFresh();
        }
    }

    public interface OnPullLoadListener{
        void reFresh();
        void loadMore();
    }

    public void setOnPullLoadMoreListener(OnPullLoadListener listener){
        mOnPullLoadListener = listener;
    }
}
