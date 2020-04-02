package pl.mzuchnik.springpracadomowa6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mzuchnik.springpracadomowa6.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private List<Movie> movies;

    @Autowired
    public MovieServiceImpl(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public List<Movie> getMoviesByName(String name) {
        return movies.stream()
                .filter(movie -> movie.getName()
                .equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
}
