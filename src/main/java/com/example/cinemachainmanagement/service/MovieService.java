package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.entities.Movie;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MovieService {
    public Movie findMovieByName(String nameMovie);

    List<Movie> findShowingMovie();
}
