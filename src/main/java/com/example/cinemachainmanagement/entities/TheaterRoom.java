package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TheaterRooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "seating_capacity")
    private int seatingCapacity;

    @Column(name = "room_type")
    private String roomType;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Seat> seats;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Showtime> showTimes;
}
