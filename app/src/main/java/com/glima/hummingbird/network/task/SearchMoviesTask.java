package com.glima.hummingbird.network.task;

import android.os.AsyncTask;
import android.util.Log;

import com.glima.hummingbird.BuildConfig;
import com.glima.hummingbird.model.Movie;
import com.glima.hummingbird.network.MoviesCallBack;
import com.glima.hummingbird.network.deserialize.MoviesDeserializer;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.glima.hummingbird.BuildConfig.API_URL;

/**
 * Created by gustavo on 18/03/17.
 */

public class SearchMoviesTask extends AsyncTask<String, Void, List<Movie>> {
    private final String API_KEY = "?api_key=".concat(BuildConfig.API_KEY);
    private final String SEARCH_PATH = "search/collection";
    private final String SEARCH_QUERY = "&query=";
    private final String PAGE_QUERY = "&page=";
    private HttpURLConnection mUrlConnection;
    private final MoviesCallBack mCallBack;
    private String mPage = "1";
    private String mQuery = "";

    public SearchMoviesTask(MoviesCallBack callBack) {
        mCallBack = callBack;
    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        if (params != null) {
            mQuery = params[0];

            if (params[1] != null)
                mPage = params[1];
        }

        try {
            URL url = new URL(API_URL
                    .concat(SEARCH_PATH)
                    .concat(API_KEY)
                    .concat(SEARCH_QUERY.concat(encodeQuery(mQuery)))
                    .concat(PAGE_QUERY.concat(mPage)));

            mUrlConnection = (HttpURLConnection) url.openConnection();
            mUrlConnection.setRequestMethod("GET");
            mUrlConnection.connect();

            return MoviesDeserializer.deserialize(mUrlConnection.getInputStream());

        } catch (Exception e) {
            Log.d("parse error", e.getMessage());
            return new ArrayList<>();
        }
    }

    private String encodeQuery(String mQuery) {
        return mQuery.replace(" ","+");
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        mCallBack.onFetchMoviesCompleted(movies);
    }
}
