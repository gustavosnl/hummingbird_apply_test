package com.glima.hummingbird.view.list;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.glima.hummingbird.R;
import com.glima.hummingbird.databinding.ActivityMovieListBinding;
import com.glima.hummingbird.model.Movie;
import com.glima.hummingbird.network.callback.MoviesCallBack;
import com.glima.hummingbird.network.task.ListPopularMoviesTask;
import com.glima.hummingbird.network.task.SearchMoviesTask;
import com.glima.hummingbird.view.BaseActivity;
import com.glima.hummingbird.view.model.MovieListViewModel;

import java.util.List;

import static android.app.SearchManager.QUERY;
import static java.lang.String.valueOf;

public class MoviesListActivity extends BaseActivity implements MoviesCallBack, PaginationCallBack {

    private RecyclerView mList;
    private ProgressBar mProgress;
    private MovieListViewModel mViewModel;
    private Handler mHandler;
    private OnScrollListener mListener;

    @Override
    protected void init() {
        mProgress = ((ActivityMovieListBinding) mViewDataBinding).progressBar;
        mList = ((ActivityMovieListBinding) mViewDataBinding).movieList;
        mList.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.column_span_count)));
        mList.addItemDecoration(new ListDividerDecoration(this));
        mList.setAdapter(new MoviesAdapter());
        mListener = new OnScrollListener(this);
        mList.addOnScrollListener(mListener);

        mHandler = new Handler();
        mViewModel = new MovieListViewModel();
        ((ActivityMovieListBinding) mViewDataBinding).setMovieList(mViewModel);

        doRequest();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("onNewIntent", intent.getStringExtra(QUERY));
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null && (mViewModel.getQuery() == null || !mViewModel.getQuery().equals(bundle.getString(QUERY)))) {
            mViewModel.updateQuery(bundle.getString(QUERY));
            clearList();
            doSearchMoviesRequest(1);
        }
    }

    private void doSearchMoviesRequest(int page) {
        new SearchMoviesTask(this).execute(mViewModel.getQuery(), valueOf(page));
        Log.d("serch", mViewModel.getQuery());
    }

    protected void doPopularMoviesRequest(int page) {
        new ListPopularMoviesTask(this).execute(valueOf(page));
    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbar = ((ActivityMovieListBinding) mViewDataBinding).includeToolbar.toolbar;
        setSupportActionBar(toolbar);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_movie_list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(getQueryListener());
        return true;
    }

    @Override
    public void onFetchMoviesCompleted(List<Movie> movies) {
        mViewModel.addMovies(movies);
        ((MoviesAdapter) mList.getAdapter()).reload(mViewModel);
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadNextPage(int page) {
        if (mViewModel.isQueryEmpty()) {
            doPopularMoviesRequest(page);
        } else {
            doSearchMoviesRequest(page);
        }
    }

    private SearchView.OnQueryTextListener getQueryListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.updateQuery(query);
                doRequest();
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                clearList();
                mHandler.removeCallbacksAndMessages(null);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewModel.updateQuery(newText);
                        doRequest();
                        Log.d("textChanged", newText);
                    }
                }, 500);
                return false;
            }
        };
    }

    private void clearList() {
        mListener.resetPagination();
        mViewModel.clearList();
    }

    private void doRequest() {
        if (mViewModel.isSearch()) {
            doSearchMoviesRequest(1);
        } else {
            doPopularMoviesRequest(1);
        }
    }
}