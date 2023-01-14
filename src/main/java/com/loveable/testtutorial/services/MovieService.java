package com.loveable.testtutorial.services;

import com.loveable.testtutorial.model.Movie;

import java.util.List;

public interface MovieService {
    Movie save(Movie movie);

    List<Movie> getAllMovies();

    Movie getMovieById(Long id);

    Movie updateMovie(Movie movie, Long id);

    void deleteMovie(Long id);
}
