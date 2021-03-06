package com.glima.hummingbird.view.model;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.glima.hummingbird.R;
import com.glima.hummingbird.model.Movie;
import com.glima.hummingbird.network.callback.ImageCallBack;
import com.glima.hummingbird.network.task.LoadImageTask;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static android.support.v4.content.ContextCompat.getDrawable;
import static com.glima.hummingbird.BuildConfig.API_IMG_URL;
import static java.util.Locale.US;

/**
 * Created by gustavo on 16/03/17.
 */

public class MovieItemViewModel implements Serializable {

    private Movie movie;
    private static CallbackHandler handler;

    public MovieItemViewModel(Movie movie) {
        this.movie = movie;
        handler = new CallbackHandler();
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public String getYear() {
        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy", US);
        try {
            return outputDateFormat.format(inputDateFormat.parse(movie.getDate()));
        } catch (ParseException e) {
            return "";
        }
    }

    public String getOverview() {
        return movie.getOverview();
    }

    public String getThumbnailUrl() {
        return API_IMG_URL.concat(movie.getThumbnail());
    }

    @BindingAdapter({"bind:thumbnailUrl"})
    public static void loadThumbnailImage(ImageView view, String imageUrl) {
        new LoadImageTask(handler, view).execute(imageUrl);
    }

    public String getPosterUrl() {
        return API_IMG_URL.concat(movie.getPoster());
    }

    @BindingAdapter({"bind:posterUrl"})
    public static void loadPosterImage(ImageView view, String imageUrl) {
        new LoadImageTask(handler, view).execute(imageUrl);
    }

    class CallbackHandler implements ImageCallBack {
        @Override
        public void onMovieImageDownloadCompleted(Bitmap movieImage, ImageView imageView) {
            imageView.setImageBitmap(movieImage);
        }

        @Override
        public void onMovieImageDownloadError(ImageView imageView) {
            Context context = imageView.getContext();
            imageView.setImageDrawable(getDrawable(context, R.drawable.poster_placeholder));
        }
    }
}
