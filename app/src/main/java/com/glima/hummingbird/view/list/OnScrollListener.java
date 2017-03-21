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
    private int mPage;

    public OnScrollListener(PaginationCallBack callBack) {
        mCallback = callBack;
        mPage = 1;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

        if (dy > 0) {
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                mCallback.loadNextPage(++mPage);
                isLoading = true;
            }

            if (isLoading && totalItemCount > totalItems) {
                isLoading = false;
                totalItems = totalItemCount;
            }
        }
    }

    public void resetPagination() {
        mPage = 1;
        totalItems = 0;
        isLoading = true;
    }
}
