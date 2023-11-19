package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TheaterRooms")
@Getter
@Setter
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

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Seat> seats;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Showtime> showTimes;

    public TheaterRoom(TheaterRoom room) {
        this.roomId = room.roomId;
        this.roomNumber = room.roomNumber;
        this.seatingCapacity = room.seatingCapacity;
        this.roomType = room.roomType;
        this.showTimes = new ArrayList<>();
    }
}
