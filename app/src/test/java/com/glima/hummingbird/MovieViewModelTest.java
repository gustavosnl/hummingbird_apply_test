package com.glima.hummingbird;

import com.glima.hummingbird.network.deserialize.MoviesDeserializer;
import com.glima.hummingbird.view.model.MovieItemViewModel;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by gustavo on 19/03/17.
 */

public class MovieViewModelTest {

    final String CONCAT_THUMBNAIL = "http://image.tmdb.org/t/p/w320//45Y1G5FEgttPAwjTYic6czC9xCn.jpg";
    final String CONCAT_POSTER = "http://image.tmdb.org/t/p/w320//5pAGnkFYSsFJ99ZxDIYnhQbQFXs.jpg";

    MovieItemViewModel viewModel;

    @Before
    public void setup() throws IOException, JSONException {
        viewModel = new MovieItemViewModel(MoviesDeserializer.deserialize(new FileInputStream("app/src/test/res/popular_movies_sample.json")).get(0));
    }

    @Test
    public void format_fields() {
        assertThat(viewModel.getYear(), is(equalTo("2017")));
        assertThat(viewModel.getPosterUrl(), is(equalTo(CONCAT_POSTER)));
        assertThat(viewModel.getThumbnailUrl(), is(equalTo(CONCAT_THUMBNAIL)));
    }
}
