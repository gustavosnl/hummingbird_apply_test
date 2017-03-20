package com.glima.hummingbird.network.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.glima.hummingbird.network.callback.ImageCallBack;

import java.net.HttpURLConnection;
import java.net.URL;

import static android.graphics.BitmapFactory.decodeStream;

/**
 * Created by gustavo on 16/03/17.
 */

public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {

    private HttpURLConnection urlConnection;
    private ImageCallBack mCallback;
    private ImageView mImageView;

    public LoadImageTask(ImageCallBack callBack, ImageView imageView) {
        mCallback = callBack;
        mImageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            return decodeStream(urlConnection.getInputStream());

        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        mCallback.onMovieImageDownloadCompleted(bitmap, mImageView);
    }
}
