package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.entities.Movie;
import com.example.cinemachainmanagement.repositories.MovieRepository;
import com.example.cinemachainmanagement.service.MovieService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public Movie findMovieByName(String nameMovie) {
        return movieRepository.findMovieByName(nameMovie);
    }

    @Override
    public List<Movie> findShowingMovie() {
        Date currentTime = new Date();
        List<Movie> allMovies = movieRepository.findAll();
        return allMovies.stream()
                .filter(movie -> movie.getShowTimes().stream()
                        .anyMatch(showtime -> showtime.getDate().after(currentTime)))
                .toList();
    }
}
