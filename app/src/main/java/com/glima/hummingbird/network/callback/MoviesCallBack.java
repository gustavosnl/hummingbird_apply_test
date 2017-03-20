package com.glima.hummingbird.network.callback;

import com.glima.hummingbird.model.Movie;

import java.util.List;

/**
 * Created by gustavo on 15/03/17.
 */

public interface MoviesCallBack {

    void onFetchMoviesCompleted(List<Movie> movies);

    void showProgress();

}
