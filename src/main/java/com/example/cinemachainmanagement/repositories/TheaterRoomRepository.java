package com.example.cinemachainmanagement.repositories;

import com.example.cinemachainmanagement.entities.TheaterRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRoomRepository extends JpaRepository<TheaterRoom, Long> {
}
