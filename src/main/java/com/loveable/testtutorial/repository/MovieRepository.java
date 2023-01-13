package com.loveable.testtutorial.repository;

import com.loveable.testtutorial.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
