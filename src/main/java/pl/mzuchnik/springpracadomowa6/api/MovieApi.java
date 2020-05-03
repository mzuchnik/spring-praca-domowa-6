package pl.mzuchnik.springpracadomowa6.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mzuchnik.springpracadomowa6.aop.annotation.SendEmailAfter;
import pl.mzuchnik.springpracadomowa6.model.Movie;
import pl.mzuchnik.springpracadomowa6.service.MovieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies", consumes = MediaType.APPLICATION_JSON_VALUE)
public class MovieApi {

    private MovieService movieService;

    @Autowired
    public MovieApi(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<>(movieService.getMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMoviesById(@PathVariable long id) {
        Optional<Movie> movieById = movieService.getMovieById(id);
        return movieById.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    //@SendEmailAfter()
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Optional<Movie> newMovie = movieService.addMovie(movie);
        if(newMovie.isPresent()){
            return new ResponseEntity<>(newMovie.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> removeMovieById(@PathVariable long id) {
        Optional<Movie> movie = movieService.removeMovieById(id);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.ACCEPTED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovieById(@PathVariable long id, @RequestBody Movie movie) {
        Optional<Movie> updateMovie = movieService.updateMovieById(id, movie);
        return updateMovie.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    /*@GetMapping("/{name}")
    public List<Movie> getMoviesByName(@PathVariable(name = "name") String name){
        return movieService.getMoviesByName(name);
    }*/
}
