package com.glima.hummingbird.network;

import android.os.AsyncTask;

import com.glima.hummingbird.BuildConfig;
import com.glima.hummingbird.model.Movie;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.glima.hummingbird.BuildConfig.API_URL;

/**
 * Created by gustavo on 14/03/17.
 */

public class ListPopularMoviesTask extends AsyncTask<Integer, Void, List<Movie>> {

    private final String API_KEY = "?api_key=".concat(BuildConfig.API_KEY);
    private final String POPULAR_MOVIES_PATH = "discover/movie";
    private final String SORT_DESC_QUERY = "&sort_by=popularity.desc";
    private final String PAGE_QUERY = "&page=";
    private HttpURLConnection mUrlConnection;
    private final MoviesCallBack mCallBack;
    private String mPage = "1";

    public ListPopularMoviesTask(MoviesCallBack callBack) {
        mCallBack = callBack;

    }

    @Override
    protected List<Movie> doInBackground(Integer... params) {
        if (params != null) {
            mPage = String.valueOf(params[0]);
        }

        try {
            URL url = new URL(API_URL
                    .concat(POPULAR_MOVIES_PATH)
                    .concat(API_KEY)
                    .concat(PAGE_QUERY.concat(mPage))
                    .concat(SORT_DESC_QUERY));

            mUrlConnection = (HttpURLConnection) url.openConnection();
            mUrlConnection.setRequestMethod("GET");
            mUrlConnection.connect();

          return MoviesDeserializer.deserialize(mUrlConnection.getInputStream());

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        mCallBack.onFetchMoviesCompleted(movies);
    }
}
