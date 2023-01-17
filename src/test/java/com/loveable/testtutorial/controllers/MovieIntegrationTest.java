package com.loveable.testtutorial.controllers;

import com.loveable.testtutorial.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieIntegrationTest {

    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";
    private static RestTemplate restTemplate;
    @Autowired
    private MovieRepository movieRepository;

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
    void create() {
    }

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