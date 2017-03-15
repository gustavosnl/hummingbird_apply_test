package com.glima.hummingbird.network;

/**
 * Created by gustavo on 14/03/17.
 */

public interface NetworkTask<T> {

    void onNetworkOperationFinished(T result);

}
