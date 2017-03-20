package com.glima.hummingbird.network.task;

import com.glima.hummingbird.network.callback.MoviesCallBack;

import java.net.MalformedURLException;
import java.net.URL;

import static com.glima.hummingbird.BuildConfig.API_URL;

/**
 * Created by gustavo on 18/03/17.
 */

public class SearchMoviesTask extends MoviesTask {

    private final String SEARCH_PATH = "search/movie";
    private final String SEARCH_QUERY = "&query=";
    private final Integer QUERY_INDEX = 0;
    private final Integer PAGE_INDEX = 1;


    public SearchMoviesTask(MoviesCallBack callBack) {
        super(callBack);
    }

    @Override
    protected URL buildUrl(String... params) throws MalformedURLException {
        return new URL(API_URL
                .concat(SEARCH_PATH)
                .concat(API_KEY)
                .concat(SEARCH_QUERY.concat(encodeQuery(params[QUERY_INDEX])))
                .concat(PAGE_QUERY.concat(params[PAGE_INDEX])));
    }

    private String encodeQuery(String mQuery) {
        return mQuery.replace(" ","+");
    }
}
