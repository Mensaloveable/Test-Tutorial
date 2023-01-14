package com.loveable.testtutorial.services.impl;

import com.loveable.testtutorial.model.Movie;
import com.loveable.testtutorial.repository.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    @DisplayName("Should save Avatar")
    void save() {
        Movie avatar = new Movie();
        avatar.setId(1L);
        avatar.setName("Avatar");
        avatar.setGenre("Action");
        avatar.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));

        when(movieRepository.save(any(Movie.class))).thenReturn(avatar);

        Movie newMovie = movieService.save(avatar);

        assertNotNull(newMovie);
        assertThat(newMovie.getName()).isEqualTo("Avatar");
    }

    @Test
    @DisplayName("Should return 2 as number of movies")
    void getAllMovies() {
        Movie avatar = new Movie();
        avatar.setId(1L);
        avatar.setName("Avatar");
        avatar.setGenre("Action");
        avatar.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));

        Movie titanic = new Movie();
        titanic.setId(2L);
        titanic.setName("Titanic");
        titanic.setGenre("Romance");
        titanic.setReleaseDate(LocalDate.of(1999, Month.MAY, 22));

        List<Movie> movieList = new ArrayList<>();
        movieList.add(avatar);
        movieList.add(titanic);

        when(movieRepository.findAll()).thenReturn(movieList);

        List<Movie> movies = movieService.getAllMovies();

        assertNotNull(movies);
        assertEquals(2, movies.size());
    }

    @Test
    @DisplayName("Should return avatar with id 1L")
    void getMovieById() {
        Movie avatar = new Movie();
        avatar.setId(1L);
        avatar.setName("Avatar");
        avatar.setGenre("Action");
        avatar.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));

        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(avatar));

        Movie movieById = movieService.getMovieById(1L);

        assertNotNull(movieById);
        assertThat(movieById.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Should throw exception")
    void getMovieByIdForException() {
        Movie avatar = new Movie();
        avatar.setId(1L);
        avatar.setName("Avatar");
        avatar.setGenre("Action");
        avatar.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));

        when(movieRepository.findById(1L)).thenReturn(Optional.of(avatar));

        assertThrows(RuntimeException.class, () -> movieService.getMovieById(2L));
    }

    @Test
    void updateMovie() {
    }

    @Test
    void deleteMovie() {
    }
}