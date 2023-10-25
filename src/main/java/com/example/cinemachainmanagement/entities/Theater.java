package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Theaters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theater implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private Long theaterId;

    private String name;

    private String location;

    @Column(name = "opening_date")
    private Date openingDate;

    @Column(name = "total_seating_capacity")
    private int totalSeatingCapacity;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TheaterRoom> rooms;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Movie> movies;
}
