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

public class ListPopularMoviesTask extends AsyncTask<String, Void, List<Movie>> {

    private final String API_KEY = "?api_key=".concat(BuildConfig.API_KEY);
    private final String POPULAR_Movies_PATH = "discover/movie";
    private final String SORT_DESC_QUERY_PARAMETER = "&sort_by=popularity.desc";
    private HttpURLConnection urlConnection;
    private final MoviesCallBack mCallBack;

    public ListPopularMoviesTask(MoviesCallBack callBack) {
        mCallBack = callBack;
    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        try {
            URL url = new URL(API_URL
                    .concat(POPULAR_Movies_PATH)
                    .concat(API_KEY)
                    .concat(SORT_DESC_QUERY_PARAMETER));

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

          return MoviesDeserializer.deserialize(urlConnection.getInputStream());

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        mCallBack.onFetchMoviesCompleted(movies);
    }
}
