package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Theaters")
@Getter
@Setter
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

    @OneToMany(mappedBy = "theater",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TheaterRoom> rooms;

    @ManyToMany
    @JoinTable(
            name = "theater_movie",
            joinColumns = @JoinColumn(name = "theater_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies;

    public Theater(Theater considerTheater) {
        this.theaterId = considerTheater.theaterId;

        this.name = considerTheater.name;

        this.location = considerTheater.location;

        this.openingDate = considerTheater.openingDate;

        this.totalSeatingCapacity = considerTheater.totalSeatingCapacity;

        this.rooms = new ArrayList<>();
    }
}
