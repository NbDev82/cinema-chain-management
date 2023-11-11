package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.entities.Movie;
import com.example.cinemachainmanagement.entities.Showtime;
import com.example.cinemachainmanagement.entities.Theater;
import com.example.cinemachainmanagement.entities.TheaterRoom;

import java.util.List;
import java.util.Optional;

public interface TheaterService {
    public List<Theater> filterTheatersByLocation(List<Theater> theaters, String Location);

    List<Theater> filterTheatersByTheaterName(List<Theater> theatersHasMovie, String selectedTheater);

    Optional<TheaterRoom> getTheaterRoomById(Long roomId);

    List<Theater> filterTheatersByRoom(List<Showtime> showtimes);
}
