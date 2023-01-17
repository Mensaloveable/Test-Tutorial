package com.loveable.testtutorial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tbl_movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long Id;
    private String name;
    private String genre;
    private LocalDate releaseDate;

}
