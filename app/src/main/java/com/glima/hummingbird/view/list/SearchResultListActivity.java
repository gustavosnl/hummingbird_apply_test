package com.glima.hummingbird.view.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.glima.hummingbird.R;
import com.glima.hummingbird.databinding.ActivityMovieListBinding;
import com.glima.hummingbird.model.Movie;
import com.glima.hummingbird.network.MoviesCallBack;
import com.glima.hummingbird.network.task.SearchMoviesTask;
import com.glima.hummingbird.view.BaseActivity;
import com.glima.hummingbird.view.model.MovieListViewModel;

import java.util.List;

import static android.app.SearchManager.QUERY;
import static java.lang.String.valueOf;

/**
 * Created by gustavo on 18/03/17.
 */

public class SearchResultListActivity extends BaseActivity implements MoviesCallBack, PaginationCallBack {

    private String mQuery;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            Intent intent = new Intent();
            intent.putExtra(QUERY, savedInstanceState.getString(QUERY));
            setIntent(intent);
        }

        handleIntent(getIntent());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(QUERY, mQuery);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void init() {
        progressBar = ((ActivityMovieListBinding) viewDataBinding).progressBar;
        recyclerView = ((ActivityMovieListBinding) viewDataBinding).movieList;
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.column_span_count)));
        recyclerView.addItemDecoration(new ListDividerDecoration(this));
        recyclerView.setAdapter(new MoviesAdapter());
        recyclerView.addOnScrollListener(new OnScrollListener(this));
    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbar = ((ActivityMovieListBinding) viewDataBinding).includeToolbar.toolbar;
        setSupportActionBar(toolbar);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_movie_list;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            mQuery = intent.getStringExtra(QUERY);
            if (mQuery != null)
                doRequest(1);
        }


    }

    @Override
    public void loadNextPage(int page) {
        doRequest(page);
    }

    protected void doRequest(int page) {
        new SearchMoviesTask(this).execute(mQuery, valueOf(page));
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
}
