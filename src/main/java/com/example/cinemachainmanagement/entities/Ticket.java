package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "purchase_time")
    private Date purchaseTime;

    @Column(name = "ticket_status")
    private String ticketStatus;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    Showtime showTime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
