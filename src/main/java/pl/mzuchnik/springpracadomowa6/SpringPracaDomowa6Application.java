package pl.mzuchnik.springpracadomowa6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.mzuchnik.springpracadomowa6.model.Movie;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringPracaDomowa6Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringPracaDomowa6Application.class, args);
    }

    @Bean
    public List<Movie> getMoviesList()
    {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            movies.add(new Movie(i,"Film nr "+(i+1), 1992+i,"Producer " + (i+1)));
        }
        return movies;
    }
}
