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
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieIntegrationTest {

    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";
    private static RestTemplate restTemplate;
    @Autowired
    private MovieRepository movieRepository;
    private Movie avatar, titanic;

    @BeforeAll
    static void init(){
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    void setUp() {
        baseUrl = "%s:%d/movies".formatted(baseUrl, port);
    }

    @AfterEach
    void tearDown() {
        movieRepository.deleteAll();
    }

    @Test
    void shouldCreateNewMovie() {
        avatar = Movie.builder()
                .name("Avatar")
                .genre("Action")
                .releaseDate(LocalDate.of(2000, Month.APRIL, 22))
                .build();

        Movie movie = restTemplate.postForObject(baseUrl, avatar, Movie.class);

        assertNotNull(movie);
        assertThat(movie.getId()).isNotNull();
    }

    @Test
    void shouldFetchAllMovies() {
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

        restTemplate.postForObject(baseUrl, avatar, Movie.class);
        restTemplate.postForObject(baseUrl, titanic, Movie.class);

        List movieList = restTemplate.getForObject(baseUrl, List.class);

        assertNotNull(movieList);
        assertThat(movieList.size()).isEqualTo(2);
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