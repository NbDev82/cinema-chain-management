package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "account_balance")
    private int accountBalance;

    @Column(name="password_hash")
    private String passHash;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<SnackOrder> snackOrders;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Ticket> tickets;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Transaction> transactions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PaymentMethod> paymentMethods;
}
