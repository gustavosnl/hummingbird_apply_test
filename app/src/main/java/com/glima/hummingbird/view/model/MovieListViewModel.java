package com.glima.hummingbird.view.model;

import android.databinding.BaseObservable;

import com.glima.hummingbird.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 15/03/17.
 */

public class MovieListViewModel extends BaseObservable{

    private List<Movie> mMovies = new ArrayList<>();
    private String mQuery = "";

    public List<Movie> getMovies() {
        return mMovies;
    }

    public boolean isEmpty() {
        return mMovies.isEmpty();
    }

    public String getQuery() {
        return mQuery;
    }

    public boolean isSearch() {
        return !"".equals(mQuery);
    }

    public boolean isSearchResultEmpty() {
        return isEmpty() && isSearch();
    }

    public void addMovies(List<Movie> movies) {
        mMovies.addAll(movies);
    }

    public void updateQuery(String query) {
        mQuery = query;
    }

    public boolean isQueryEmpty() {
        return "".equals(mQuery);
    }

    public void clearList() {
        mMovies.clear();
    }
}
