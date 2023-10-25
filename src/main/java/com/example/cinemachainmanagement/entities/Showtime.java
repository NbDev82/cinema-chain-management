package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Showtimes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Showtime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id")
    private Long showtimeId;

    private Date date;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private TheaterRoom room;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
