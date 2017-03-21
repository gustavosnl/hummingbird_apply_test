package com.glima.hummingbird.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gustavo on 15/03/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected ViewDataBinding mViewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayout());
        setupToolbar();
        init();
    }

    protected abstract void init();

    protected abstract void setupToolbar();

    public abstract int getLayout();
}
