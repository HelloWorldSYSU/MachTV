package com.sysumach.machtv;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private static final int [] ATTR = new int[]{ android.R.attr.listDivider };
    private Drawable mDevider;

    public MyDecoration(Context context){
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(ATTR);
        mDevider = typedArray.getDrawable(0);
        typedArray.recycle();
    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizonaline(c, parent, state);
    }

    private void drawHorizonaline(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i ++){
            View view = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)view.getLayoutParams();

            int top = view.getBottom() + params.bottomMargin;
            int bottom = top + mDevider.getIntrinsicHeight();
            mDevider.setBounds(left, top, right, bottom);
            mDevider.draw(c);
        }

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, mDevider.getMinimumHeight());
    }
}
