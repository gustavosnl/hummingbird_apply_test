package com.glima.hummingbird.view.list;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.glima.hummingbird.R;

/**
 * Created by gustavo on 16/03/17.
 */

public class ListDividerDecoration extends RecyclerView.ItemDecoration {
    private final Context mContext;

    public ListDividerDecoration(Context context) {
        this.mContext = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = mContext.getResources().getDimensionPixelSize(R.dimen.list_default_separator);
        outRect.left = mContext.getResources().getDimensionPixelSize(R.dimen.list_default_separator);
        outRect.right = mContext.getResources().getDimensionPixelSize(R.dimen.list_default_separator);
        outRect.top = mContext.getResources().getDimensionPixelSize(R.dimen.list_default_separator);
    }
}
