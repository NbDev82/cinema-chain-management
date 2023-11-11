package com.example.cinemachainmanagement.repositories;

import com.example.cinemachainmanagement.entities.Movie;
import com.example.cinemachainmanagement.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    @Query(value = "SELECT * FROM movies WHERE title = :nameMovie", nativeQuery = true)
    Movie findMovieByName(String nameMovie);
}
