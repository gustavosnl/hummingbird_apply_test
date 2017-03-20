package com.glima.hummingbird.network.task;

import com.glima.hummingbird.network.callback.MoviesCallBack;

import java.net.MalformedURLException;
import java.net.URL;

import static com.glima.hummingbird.BuildConfig.API_URL;

/**
 * Created by gustavo on 14/03/17.
 */

public class ListPopularMoviesTask extends MoviesTask {

    private final String POPULAR_MOVIES_PATH = "discover/movie";
    private final String SORT_DESC_QUERY = "&sort_by=popularity.desc";

    public ListPopularMoviesTask(MoviesCallBack callBack) {
        super(callBack);
    }

    @Override
    protected URL buildUrl(String... params) throws MalformedURLException {
        return new URL(API_URL
                .concat(POPULAR_MOVIES_PATH)
                .concat(API_KEY)
                .concat(PAGE_QUERY.concat(params[0]))
                .concat(SORT_DESC_QUERY));
    }
}
