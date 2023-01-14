package com.loveable.testtutorial.services;

import com.loveable.testtutorial.model.Movie;

import java.util.List;

public interface MovieService {
    public Movie save(Movie movie);

    public List<Movie> getAllMovies();

    public Movie getMovieById(Long id);

    public Movie updateMovie(Movie movie, Long id);

    public void deleteMovie(Long id);
}
