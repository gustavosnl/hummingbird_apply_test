package com.glima.hummingbird.view.list;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.glima.hummingbird.R;
import com.glima.hummingbird.databinding.ActivityMainBinding;
import com.glima.hummingbird.model.Movie;
import com.glima.hummingbird.network.ListPopularMoviesTask;
import com.glima.hummingbird.network.MoviesCallBack;
import com.glima.hummingbird.view.BaseActivity;
import com.glima.hummingbird.view.list.model.MovieListViewModel;

import java.util.List;

public class MainActivity extends BaseActivity implements MoviesCallBack, OnScrollFinishedCallBack {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private OnScrollListener mScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        progressBar = ((ActivityMainBinding) viewDataBinding).progressBar;
        recyclerView = ((ActivityMainBinding) viewDataBinding).movieList;
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.column_span_count)));
        recyclerView.addItemDecoration(new ListDividerDecoration(this));
        recyclerView.setAdapter(new MoviesAdapter());
        recyclerView.addOnScrollListener(new OnScrollListener(this));
        new ListPopularMoviesTask(this).execute(1);
    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbar = ((ActivityMainBinding) viewDataBinding).includeToolbar.toolbar;
        setSupportActionBar(toolbar);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onFetchMoviesCompleted(List<Movie> movies) {
        MovieListViewModel viewModel = new MovieListViewModel(movies);
        ((MoviesAdapter) recyclerView.getAdapter()).attachViewModel(viewModel);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFetchMoviesError() {

    }

    @Override
    public void loadNextPage(int page) {
        new ListPopularMoviesTask(this).execute(page);

    }
}
