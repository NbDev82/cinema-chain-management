package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Theaters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private int theaterId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private TheaterRoom room;

    private String name;
    private String location;

    @Column(name = "opening_date")
    private Date openingDate;

    @Column(name = "total_seating_capacity")
    private int totalSeatingCapacity;

    // Constructors, getters, and setters
}
