package com.glima.hummingbird.network;

import com.glima.hummingbird.model.Movie;

import java.util.List;

/**
 * Created by gustavo on 15/03/17.
 */

public interface FilmsCallBack {

    void onFetchFilmsCompleted(List<Movie> movies);

    void onFetchFilmsError();
}
