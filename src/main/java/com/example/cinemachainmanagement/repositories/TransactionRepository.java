package com.example.cinemachainmanagement.repositories;

import com.example.cinemachainmanagement.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
