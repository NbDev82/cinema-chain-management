package com.example.cinemachainmanagement.entities;

import com.example.cinemachainmanagement.enums.EMethod;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;

    @Column(name = "transaction_status")
    private boolean transactionStatus;

    @Column(name = "method_type")
    @Enumerated(EnumType.STRING)
    private EMethod methodType;

    @Column(name = "total_amount", nullable = false)
    private int totalAmount;

    @Column(name = "security_code")
    private String securityCode;

    @Temporal(TemporalType.DATE)
    private Date created;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "snack_order_id")
    private Orders orders;
}
