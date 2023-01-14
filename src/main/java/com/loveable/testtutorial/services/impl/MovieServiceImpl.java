package com.loveable.testtutorial.services.impl;

import com.loveable.testtutorial.model.Movie;
import com.loveable.testtutorial.repository.MovieRepository;
import com.loveable.testtutorial.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public Movie updateMovie(Movie movie, Long id) {
        Movie existingMovie = movieRepository.findById(id).orElse(null);
        assert existingMovie != null;
        existingMovie.setName(movie.getName());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setReleaseDate(movie.getReleaseDate());

        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Long id) {
        Movie existingMovie = movieRepository.findById(id).orElse(null);
        assert existingMovie != null;
        movieRepository.delete(existingMovie);
    }
}
