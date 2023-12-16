package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.security.Timestamp;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;

    private String status;

    @Column(name = "total_amount", nullable = false)
    private int totalAmount;

    @Column(name = "security_code")
    private String securityCode;

    private Timestamp created;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "snack_order_id", nullable = false)
    private Orders orders;
}
