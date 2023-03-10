package com.loveable.testtutorial.controllers;

import com.loveable.testtutorial.model.Movie;
import com.loveable.testtutorial.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieIntegrationTest {

    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";
    private static RestTemplate restTemplate;
    @Autowired
    private MovieRepository movieRepository;
    private Movie avatar, titanic, movie1, movie2;

    @BeforeAll
    static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    void setUp() {
        baseUrl = "%s:%d/movies".formatted(baseUrl, port);

        avatar = Movie.builder()
                .name("Avatar")
                .genre("Action")
                .releaseDate(LocalDate.of(2000, Month.APRIL, 22))
                .build();
        titanic = Movie.builder()
                .name("Titanic")
                .genre("Romance")
                .releaseDate(LocalDate.of(1999, Month.MAY, 22))
                .build();

        movie1 = movieRepository.save(avatar);
        movie2 = movieRepository.save(titanic);
    }

    @AfterEach
    void tearDown() {
        movieRepository.deleteAll();
    }

    @Test
    void shouldCreateNewMovie() {

        assertNotNull(movie1);
        assertThat(movie1.getId()).isNotNull();
    }

    @Test
    void shouldFetchAllMovies() {
        List movieList = restTemplate.getForObject(baseUrl, List.class);

        assertNotNull(movieList);
        assertThat(movieList.size()).isEqualTo(2);
    }

    @Test
    void shouldFetchOneMovie() {
        assert movie1 != null;
        Movie movie = restTemplate.getForObject(baseUrl + "/" + movie1.getId(), Movie.class);

        assertNotNull(movie);
        assertEquals("Avatar", movie.getName());
    }

    @Test
    void shouldUpdateMovie() {
        assert movie1 != null;
        movie1.setGenre("Fantasy");

        restTemplate.put(baseUrl + "/{id}", movie1, movie1.getId());

        Movie updatedMovie = restTemplate.getForObject(baseUrl + "/" + movie1.getId(), Movie.class);

        assertNotNull(updatedMovie);
        assertThat(updatedMovie.getGenre()).isEqualTo("Fantasy");
    }

    @Test
    void shouldDeleteMovie() {
        assert movie1 != null;
        restTemplate.delete(baseUrl + "/" + movie1.getId());

        int size = movieRepository.findAll().size();

        assertEquals(1, size);
    }
}