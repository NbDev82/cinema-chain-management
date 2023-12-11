package com.example.cinemachainmanagement.repositories;

import com.example.cinemachainmanagement.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Long> {
}
