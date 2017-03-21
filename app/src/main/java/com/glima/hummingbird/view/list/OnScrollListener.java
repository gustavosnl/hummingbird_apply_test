package com.glima.hummingbird.view.list;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by gustavo on 16/03/17.
 */

public class OnScrollListener extends RecyclerView.OnScrollListener {

    private PaginationCallBack mCallback;
    private boolean isLoading = true;
    private int totalItems = 0;
    private int mPage = 1;

    public OnScrollListener(PaginationCallBack callBack) {
        mCallback = callBack;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

        if (dy > 0) {
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

            if (isLoading && totalItemCount > totalItems) {
                isLoading = false;
                totalItems = totalItemCount;
            }

            if (!isLoading && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                mCallback.loadNextPage(++mPage);
                isLoading = true;
            }
        }
    }
}
