package pl.mzuchnik.springpracadomowa6.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mzuchnik.springpracadomowa6.aop.annotation.SendEmailAfter;
import pl.mzuchnik.springpracadomowa6.model.Movie;
import pl.mzuchnik.springpracadomowa6.service.MovieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieApi {

    private MovieService movieService;

    @Autowired
    public MovieApi(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getMovies(@RequestParam(name = "name", required = false) Optional<String> name) {
        if (name.isPresent())
        {
            return movieService.getMoviesByName(name.get());
        }
        return movieService.getMovies();
    }

    @PostMapping
    @SendEmailAfter()
    public void addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
    }

}
