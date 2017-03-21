package com.glima.hummingbird.network.callback;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by gustavo on 16/03/17.
 */

public interface ImageCallBack {

    void onMovieImageDownloadCompleted(Bitmap movieImage, ImageView imageView);

    void onMovieImageDownloadError(ImageView imageView);
}
