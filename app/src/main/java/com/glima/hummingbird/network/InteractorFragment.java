package com.glima.hummingbird.network;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.glima.hummingbird.BuildConfig;
import com.glima.hummingbird.model.Film;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by gustavo on 14/03/17.
 */

public class InteractorFragment extends Fragment implements Interactor {

    private HttpURLConnection urlConnection;
    private String mBaseUrl = BuildConfig.API_URL;

    public static InteractorFragment getInstance(FragmentManager fragmentManager) {
        InteractorFragment interactorFragment = new InteractorFragment();
        fragmentManager.beginTransaction().add(interactorFragment, "").commit();
        return interactorFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        // Host Activity will handle callbacks from task.
//        mCallback = (DownloadCallback) context;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        // Clear reference to host Activity to avoid memory leak.
//        mCallback = null;
//    }
//
//    @Override
//    public void onDestroy() {
//        // Cancel task when Fragment is destroyed.
//        cancelDownload();
//        super.onDestroy();
//    }
//
//    /**
//     * Start non-blocking execution of DownloadTask.
//     */
//    public void startDownload() {
//        cancelDownload();
//        mDownloadTask = new DownloadTask();
//        mDownloadTask.execute(mUrlString);
//    }
//
//    /**
//     * Cancel (and interrupt if necessary) any ongoing DownloadTask execution.
//     */
//    public void cancelDownload() {
//        if (mDownloadTask != null) {
//            mDownloadTask.cancel(true);
//        }
//    }

    @Override
    public void onNetworkOperationFinished(List<Film> result) {

    }

    @Override
    public void fetchPopularFilms() {

    }
}
