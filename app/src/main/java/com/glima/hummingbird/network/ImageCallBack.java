package com.glima.hummingbird.network;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by gustavo on 16/03/17.
 */

public interface ImageCallBack {

    void onMovieImageDownloadCompleted(Bitmap movieImage, ImageView imageView);

    void onMovieImageDownloadError();
}
