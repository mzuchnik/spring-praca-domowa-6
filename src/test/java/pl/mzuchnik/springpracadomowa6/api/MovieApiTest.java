package pl.mzuchnik.springpracadomowa6.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.mzuchnik.springpracadomowa6.model.Movie;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MovieApiTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void should_get_movies() throws Exception {
        mockMvc.perform(get("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(10)));
    }

    @Test
    void should_get_movie_by_id() throws Exception {
        mockMvc.perform(get("/movies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());

    }

    @Test
    void should_add_movie() throws Exception {
        mockMvc.perform(post("/movies")
                .content(asJsonString(new Movie(11,"Super film",1337,"Pikazo")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",is("Super film")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year",is(1337)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void removeMovieById() throws Exception {
        mockMvc.perform(delete("/movies/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    void updateMovieById() throws Exception {
        mockMvc.perform(put("/movies/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(new Movie(0,"Mega Film",1212,"Ja"))))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mega Film"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(1212))
                .andExpect(MockMvcResultMatchers.jsonPath("$.producer").value("Ja"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}