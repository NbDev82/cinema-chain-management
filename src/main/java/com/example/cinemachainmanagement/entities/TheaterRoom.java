package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TheaterRooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;

    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "seating_capacity")
    private int seatingCapacity;

    @Column(name = "room_type")
    private String roomType;
}
