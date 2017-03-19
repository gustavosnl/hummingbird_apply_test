package com.glima.hummingbird;

import com.glima.hummingbird.model.Movie;
import com.glima.hummingbird.network.deserialize.MoviesDeserializer;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by gustavo on 19/03/17.
 */

public class MovieListParseTest {

    final String SYNOPSIS = "In the near future, a weary Logan cares for an ailing Professor X in a hide out on the Mexican border. But Logan's attempts to hide from the world and his legacy are up-ended when a young mutant arrives, being pursued by dark forces.";
    final String THUMBNAIL = "/45Y1G5FEgttPAwjTYic6czC9xCn.jpg";
    final String POSTER = "/5pAGnkFYSsFJ99ZxDIYnhQbQFXs.jpg";

    List<Movie> movies;

    @Before
    public void setup() throws IOException, JSONException {
        movies = MoviesDeserializer.deserialize(new FileInputStream("app/src/test/res/popular_movies_sample.json"));
    }

    @Test
    public void parse_success() {
        assertNotNull(movies);
        assertThat(movies.size(), is(equalTo(4)));

        Movie movie = movies.get(0);

        assertThat(movie.getTitle(), is(equalTo("Logan")));
        assertThat(movie.getId(), is(equalTo("263115")));
        assertThat(movie.getOverview(), is(equalTo(SYNOPSIS)));
        assertThat(movie.getThumbnail(), is(equalTo(THUMBNAIL)));
        assertThat(movie.getPoster(), is(equalTo(POSTER)));
        assertThat(movie.getDate(), is(equalTo("2017-02-28")));
    }
}
