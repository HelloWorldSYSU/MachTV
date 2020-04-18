package com.sysumach.machtv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sysumach.machtv.PlayActivity;
import com.sysumach.machtv.R;
import com.sysumach.machtv.model.Live;

public class LiveItemAdapter extends RecyclerView.Adapter<LiveItemAdapter.ViewHolder> {

    private Context mContext;
    private View view;

    public LiveItemAdapter(Context context){
        mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.live_item, null);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mIcon.setImageResource(Live.mDataIntList[position]);
        holder.mTitle.setText(Live.mDataList[position]);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayActivity.luanchActivity(mContext, Live.mUrlList[position]);
            }
        });
    }


    @Override
    public int getItemCount() {
        return Live.mDataIntList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView mIcon;
        public TextView mTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.live_img);
            mTitle = itemView.findViewById(R.id.live_text);
        }
    }
}
