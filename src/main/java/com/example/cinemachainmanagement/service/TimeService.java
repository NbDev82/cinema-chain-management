package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.entities.Movie;
import com.example.cinemachainmanagement.entities.Showtime;

import java.util.List;

public interface TimeService {
    List<Showtime> findByMovie(Movie movie);

    List<Showtime> filterBySelectedTime(List<Showtime> showTimes, String selectedMovieTime);
}
