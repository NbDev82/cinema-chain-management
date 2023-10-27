package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {
    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    private String title;

    private String description;

    @Column(name = "release_date")
    private Date releaseDate;

    private int duration;

    private String genre;

    @ManyToMany(mappedBy = "movies")
    private List<Theater> theaters;
}
