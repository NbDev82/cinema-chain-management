package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.ShowtimeDTO;
import com.example.cinemachainmanagement.entities.Movie;
import com.example.cinemachainmanagement.entities.Showtime;
import com.example.cinemachainmanagement.entities.Theater;

import java.util.List;
import java.util.Optional;

public interface TimeService {
    List<Showtime> findByMovie(Movie movie);
    Optional<Showtime> findById(Long timeId);
    List<Showtime> filterBySelectedTime(List<Showtime> showTimes, String selectedMovieTime);

    Boolean scheduleShowTime(Theater theater);

    List<ShowtimeDTO> getShowTimeByTheater(Theater theater);
}
