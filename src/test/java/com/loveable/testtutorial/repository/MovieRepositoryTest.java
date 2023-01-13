package com.loveable.testtutorial.repository;

import com.loveable.testtutorial.model.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;

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
//        assertThat(newMovie.getId()).isNotEqualTo(null);
    }

}