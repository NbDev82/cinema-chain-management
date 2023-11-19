package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "is_reserved")
    private boolean isReserved;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private TheaterRoom room;

    @OneToMany(mappedBy = "seat")
    private List<Ticket> tickets;
}
