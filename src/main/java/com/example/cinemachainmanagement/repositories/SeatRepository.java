package com.example.cinemachainmanagement.repositories;

import com.example.cinemachainmanagement.entities.Seat;
import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends CrudRepository<Seat, Long> {
}
