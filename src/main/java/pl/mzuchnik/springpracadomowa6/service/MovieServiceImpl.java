package pl.mzuchnik.springpracadomowa6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mzuchnik.springpracadomowa6.model.Movie;

import java.util.List;
import java.util.Optional;
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
    public Optional<Movie> getMovieById(long id) {
        return Optional.ofNullable(movies.get((int)id));
    }

    @Override
    public Optional<Movie> addMovie(Movie movie) {
        if(movies.add(movie)){
            return Optional.ofNullable(movie);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Movie> removeMovieById(long id) {
        Movie movie = movies.get((int)id);
        if(movies.remove(movie)){
            return Optional.ofNullable(movie);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Movie> updateMovieById(long id, Movie movie) {
        Movie updateMovie = movies.get((int)id);
        updateMovie.setName(movie.getName());
        updateMovie.setProducer(movie.getProducer());
        updateMovie.setYear(movie.getYear());
        return Optional.ofNullable(updateMovie);
    }
}
