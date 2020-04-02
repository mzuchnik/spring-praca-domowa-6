package pl.mzuchnik.springpracadomowa6.service;

import pl.mzuchnik.springpracadomowa6.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> getMoviesByName(String name);

    List<Movie> getMovies();

    void addMovie(Movie movie);
}
