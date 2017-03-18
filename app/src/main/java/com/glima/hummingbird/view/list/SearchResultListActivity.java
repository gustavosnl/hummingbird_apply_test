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
        Log.d("onCreate", getIntent().getStringExtra(QUERY));
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            Intent intent = new Intent();
            intent.putExtra(QUERY, savedInstanceState.getString(QUERY));
            setIntent(intent);
        }
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
        Log.d("onNewIntent", intent.getStringExtra(QUERY));
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null && (mQuery == null || !mQuery.equals(bundle.getString(QUERY)))) {
            mQuery = bundle.getString(QUERY);
            ((MoviesAdapter) recyclerView.getAdapter()).clear();
            doRequest(1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mQuery = query;
                doRequest(1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mQuery = newText;
                        ((MoviesAdapter) recyclerView.getAdapter()).clear();
                        doRequest(1);
                    }
                }, 500);
                return false;
            }
        });
        return true;
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
