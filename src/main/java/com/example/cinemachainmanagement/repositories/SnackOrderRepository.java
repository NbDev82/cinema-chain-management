package com.example.cinemachainmanagement.repositories;

import com.example.cinemachainmanagement.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnackOrderRepository extends JpaRepository<Orders,Long> {
}
