package com.glima.hummingbird.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.glima.hummingbird.R;
import com.glima.hummingbird.model.Film;
import com.glima.hummingbird.network.FilmsCallBack;
import com.glima.hummingbird.network.ListPopularFilmsTask;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FilmsCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new ListPopularFilmsTask(this).execute();
    }


    @Override
    public void onFetchFilmsCompleted(List<Film> films) {
        List<Film> filmsList = films;
    }

    @Override
    public void onFetchFilmsError() {

    }
}
