package com.loveable.testtutorial.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loveable.testtutorial.model.Movie;
import com.loveable.testtutorial.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MovieControllerTest {

    @MockBean
    private MovieService movieService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private Movie avatar;
    private Movie titanic;

    @BeforeEach
    void init() {
        avatar = new Movie();
        avatar.setId(1L);
        avatar.setName("Avatar");
        avatar.setGenre("Action");
        avatar.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));

        titanic = new Movie();
        titanic.setId(2L);
        titanic.setName("Titanic");
        titanic.setGenre("Romance");
        titanic.setReleaseDate(LocalDate.of(1999, Month.MAY, 22));
    }

    @Test
    void shouldCreateMovie() throws Exception {
        when(movieService.save(any(Movie.class))).thenReturn(avatar);

        mockMvc.perform(post("/movies").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(avatar))).andExpect(status().isCreated()).andExpect(jsonPath("$.name", is(avatar.getName()))).andExpect(jsonPath("$.genre", is(avatar.getGenre()))).andExpect(jsonPath("$.releaseDate", is(avatar.getReleaseDate().toString())));
    }

    @Test
    void shouldFetchAllMovies() throws Exception {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(avatar);
        movieList.add(titanic);

        when(movieService.getAllMovies()).thenReturn(movieList);

        mockMvc.perform(get("/movies")).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(movieList.size())));
    }

    @Test
    void shouldGetMovieById() throws Exception {
        when(movieService.getMovieById(anyLong())).thenReturn(avatar);

        mockMvc.perform(get("/movies/{id}", 1)) //"/movies/1" is also valid
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", is(avatar.getName()))).andExpect(jsonPath("$.genre", is(avatar.getGenre()))).andExpect(jsonPath("$.releaseDate", is(avatar.getReleaseDate().toString())));
    }

    @Test
    void shouldUpdateMovie() throws Exception {
        when(movieService.updateMovie(any(Movie.class), anyLong())).thenReturn(avatar);

        mockMvc.perform(put("/movies/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(avatar))).andExpect(status().isOk()).andExpect(jsonPath("$.name", is(avatar.getName()))).andExpect(jsonPath("$.genre", is(avatar.getGenre()))).andExpect(jsonPath("$.releaseDate", is(avatar.getReleaseDate().toString())));
    }

    @Test
    void shouldDeleteMovieFromDatabase() throws Exception {
        Movie avatar = new Movie();
        avatar.setId(1L);
        avatar.setName("Avatar");
        avatar.setGenre("Action");
        avatar.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));

        doNothing().when(movieService).deleteMovie(anyLong());

        mockMvc.perform(delete("/movies/{id}", 1L)).andExpect(status().isNoContent());
    }
}