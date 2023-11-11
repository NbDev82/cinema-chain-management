package com.example.cinemachainmanagement.repositories;

import com.example.cinemachainmanagement.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
