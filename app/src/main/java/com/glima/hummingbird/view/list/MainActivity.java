package com.glima.hummingbird.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.glima.hummingbird.R;
import com.glima.hummingbird.databinding.ActivityMainBinding;
import com.glima.hummingbird.model.Film;
import com.glima.hummingbird.network.FilmsCallBack;
import com.glima.hummingbird.network.ListPopularFilmsTask;

import java.util.List;

public class MainActivity extends BaseActivity implements FilmsCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void init() {
        new ListPopularFilmsTask(this).execute();

    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbar = ((ActivityMainBinding) viewDataBinding).includeToolbar.toolbar;
        setSupportActionBar(toolbar);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onFetchFilmsCompleted(List<Film> films) {
        List<Film> filmsList = films;
    }

    @Override
    public void onFetchFilmsError() {

    }
}
