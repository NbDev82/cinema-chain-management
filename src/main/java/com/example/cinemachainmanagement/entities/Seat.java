package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private int seatId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private TheaterRoom room;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "is_reserved")
    private boolean isReserved;
}
