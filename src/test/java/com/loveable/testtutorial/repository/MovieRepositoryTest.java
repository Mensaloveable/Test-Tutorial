package com.loveable.testtutorial.repository;

import com.loveable.testtutorial.model.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    @DisplayName("It should save the movie to the Database")
    void save() {
        //Arrange
        Movie avatar = new Movie();
        avatar.setName("Avatar");
        avatar.setGenre("Action");
        avatar.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));

        //Act
        Movie newMovie = movieRepository.save(avatar);

        //Assert
        assertNotNull(newMovie);
        assertThat(newMovie.getId()).isNotNull();
    }

    @Test
    @DisplayName("It should return he movie list with size of 2")
    void getAllSize() {
        //Arrange
        Movie avatar = new Movie();
        avatar.setName("Avatar");
        avatar.setGenre("Action");
        avatar.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));

        Movie titanic = new Movie();
        avatar.setName("Titanic");
        avatar.setGenre("Romance");
        avatar.setReleaseDate(LocalDate.of(1999, Month.MAY, 22));

        //Act
        movieRepository.save(avatar);
        movieRepository.save(titanic);
        List<Movie> allMovies = movieRepository.findAll();

        //Assert
        assertNotNull(allMovies);
        assertThat(allMovies).isNotNull();
        assertEquals(2, allMovies.size());
    }

    @Test
    @DisplayName("Confirming Movie details")
    void getMovieById() {
        //Arrange
        Movie avatar = new Movie();
        avatar.setName("Avatar");
        avatar.setGenre("Action");
        avatar.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));

        //Act
        movieRepository.save(avatar);
        Movie avatarMovie = movieRepository.findById(avatar.getId()).get();

        //Assert
        assertNotNull(avatarMovie);
        assertEquals("Action", avatarMovie.getGenre());
        assertThat(avatarMovie.getReleaseDate()).isBefore(LocalDate.of(2000, Month.APRIL, 23));
    }

    @Test
    @DisplayName("Movie Genre should be updated to \"Fantasy\"")
    void updateMovie(){
        //Arrange
        Movie avatar = new Movie();
        avatar.setName("Avatar");
        avatar.setGenre("Action");
        avatar.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));
        movieRepository.save(avatar);
        Movie avatarMovie = movieRepository.findById(avatar.getId()).get();

        //Act
        avatarMovie.setGenre("Fantasy");
        Movie newMovie = movieRepository.save(avatarMovie);

        //Assert
        assertEquals("Fantasy", newMovie.getGenre());
        assertEquals("Avatar", newMovie.getName());
    }

}