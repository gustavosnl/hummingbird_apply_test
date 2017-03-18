package com.glima.hummingbird;

import android.content.Context;

import com.glima.hummingbird.view.detail.MovieDetailsActivity;
import com.glima.hummingbird.view.model.MovieItemViewModel;

/**
 * Created by gustavo on 18/03/17.
 */

public class FlowController {

    public static void openDetailsActivity(Context originContext, MovieItemViewModel movieItemViewModel) {
        originContext.startActivity(MovieDetailsActivity.newIntent(originContext, movieItemViewModel));
    }
}
