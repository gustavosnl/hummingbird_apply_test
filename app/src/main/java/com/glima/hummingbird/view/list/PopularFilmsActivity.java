package com.glima.hummingbird.view.list;

import android.app.SearchManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.glima.hummingbird.R;
import com.glima.hummingbird.databinding.ActivityMovieListBinding;
import com.glima.hummingbird.model.Movie;
import com.glima.hummingbird.network.MoviesCallBack;
import com.glima.hummingbird.network.task.ListPopularMoviesTask;
import com.glima.hummingbird.view.BaseActivity;
import com.glima.hummingbird.view.model.MovieListViewModel;

import java.util.List;

public class PopularFilmsActivity extends BaseActivity implements MoviesCallBack, PaginationCallBack {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void init() {
        progressBar = ((ActivityMovieListBinding) viewDataBinding).progressBar;
        recyclerView = ((ActivityMovieListBinding) viewDataBinding).movieList;
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.column_span_count)));
        recyclerView.addItemDecoration(new ListDividerDecoration(this));
        recyclerView.setAdapter(new MoviesAdapter());
        recyclerView.addOnScrollListener(new OnScrollListener(this));

        doRequest(1);
    }

    protected void doRequest(int page) {
        new ListPopularMoviesTask(this).execute(page);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
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
        doRequest(page);
    }
}
