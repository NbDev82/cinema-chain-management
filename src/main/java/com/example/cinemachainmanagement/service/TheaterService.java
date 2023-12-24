package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.MovieDTO;
import com.example.cinemachainmanagement.entities.*;

import java.util.List;
import java.util.Optional;

public interface TheaterService {
    List<Theater> filterTheatersByLocation(List<Theater> theaters, String Location);

    List<Theater> filterTheatersByTheaterName(List<Theater> theatersHasMovie, String selectedTheater);

    List<Theater> filterTheatersByRoom(List<Showtime> showtimes);

    Optional<TheaterRoom> getTheaterRoomById(Long theaterId);

    Optional<Seat> getSeatById(Long seatId);
    Optional<Theater> getTheaterByTheaterName(String theaterName);

    void addMovieToTheater(MovieDTO movieDTO, Theater theater);
    void deleteMovie(MovieDTO movieDTO, Theater theater);

    List<Theater> getAllTheater();
}
