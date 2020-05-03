package pl.mzuchnik.springpracadomowa6.service;

import pl.mzuchnik.springpracadomowa6.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<Movie> getMovieById(long id);

    List<Movie> getMoviesByName(String name);

    List<Movie> getMovies();

    Optional<Movie> addMovie(Movie movie);

    Optional<Movie> removeMovieById(long id);

    Optional<Movie> updateMovieById(long id, Movie movie);
}
