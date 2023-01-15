package com.loveable.testtutorial.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loveable.testtutorial.model.Movie;
import com.loveable.testtutorial.services.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@WebMvcTest
class MovieControllerTest {

    @MockBean
    private MovieService movieService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateMovie() throws Exception {
        Movie avatar = new Movie();
        avatar.setId(1L);
        avatar.setName("Avatar");
        avatar.setGenre("Action");
        avatar.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));

        when(movieService.save(any(Movie.class))).thenReturn(avatar);

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(avatar)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(avatar.getName())))
                .andExpect(jsonPath("$.genre", is(avatar.getGenre())))
                .andExpect(jsonPath("$.releaseDate", is(avatar.getReleaseDate().toString())));


    }
    //.andExpect((ResultMatcher) contentType(MediaType.APPLICATION_JSON))
    //                .andExpect(content(objectMapper.writeValueAsString(avatar)))


    @Test
    void getAllMovies() {
    }

    @Test
    void getMovie() {
    }

    @Test
    void updateMovie() {
    }

    @Test
    void deleteMovie() {
    }
}