package com.example.cinemachainmanagement.repositories;

import com.example.cinemachainmanagement.entities.Movie;
import com.example.cinemachainmanagement.entities.Showtime;
import com.example.cinemachainmanagement.entities.Theater;
import com.example.cinemachainmanagement.entities.TheaterRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findByMovie(Movie movie);

    List<Showtime> findByRoom_Theater(Theater theater);
    @Query("SELECT s FROM Showtime s WHERE s.room = :room " +
            "AND s.date >= :startOfDay AND s.date < :endOfDay")
    List<Showtime> findByTheaterRoomAndDateBetween(TheaterRoom room, Date startOfDay, Date endOfDay);

    void deleteById(Long showtimeId);

    @Query("DELETE FROM Showtime s WHERE s.showtimeId = :showtimeId")
    void deleteByShowtimeId(@Param("showtimeId") Long showtimeId);

    @Modifying
    @Query("DELETE FROM Showtime s WHERE s.room.roomId = :roomId")
    void deleteByRoomId(@Param("roomId") Long roomId);

    @Modifying
    @Query("DELETE FROM Showtime s WHERE s.movie.movieId = :movieId")
    void deleteByMovieId(@Param("movieId") Long movieId);

    @Modifying
    @Query("DELETE FROM Showtime s WHERE s.room.roomId = :roomId AND s.movie.movieId = :movieId")
    void deleteByRoomIdAndMovieId(@Param("roomId") Long roomId, @Param("movieId") Long movieId);
}

