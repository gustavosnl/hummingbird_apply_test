package com.glima.hummingbird.flow;

import android.content.Context;

import com.glima.hummingbird.view.model.MovieItemViewModel;

import static com.glima.hummingbird.view.detail.MovieDetailsActivity.newIntent;

/**
 * Created by gustavo on 18/03/17.
 */

public class FlowController {

    public static void openDetailsActivity(Context originContext, MovieItemViewModel movieItemViewModel) {
        originContext.startActivity(newIntent(originContext, movieItemViewModel));
    }
}
