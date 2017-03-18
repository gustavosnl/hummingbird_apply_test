package com.glima.hummingbird.view.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.glima.hummingbird.R;
import com.glima.hummingbird.databinding.ActivityMovieDetailBinding;
import com.glima.hummingbird.view.BaseActivity;
import com.glima.hummingbird.view.model.MovieItemViewModel;

/**
 * Created by gustavo on 17/03/17.
 */

public class MovieDetailsActivity extends BaseActivity {
        private static final String BUNDLE_KEY_SHOW = "movie";

        private MovieItemViewModel viewModel;

        public static Intent newIntent(Context context, MovieItemViewModel movie) {
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(BUNDLE_KEY_SHOW, movie);
            intent.putExtras(bundle);

            return intent;
        }

        @Override
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            viewModel = (MovieItemViewModel) getIntent().getSerializableExtra(BUNDLE_KEY_SHOW);
            ((ActivityMovieDetailBinding) viewDataBinding).setMovie(viewModel);
        }

        @Override
        protected void init() {
        }

        @Override
        protected void setupToolbar() {
            Toolbar toolbar = ((ActivityMovieDetailBinding) viewDataBinding).collapsingToolbar.toolbar;
            setSupportActionBar(toolbar);
        }

        @Override
        public int getLayout() {
            return R.layout.activity_movie_detail;
        }
    }