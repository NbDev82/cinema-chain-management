package com.example.cinemachainmanagement.DTO;

import com.example.cinemachainmanagement.entities.TheaterRoom;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterDTO {
    private Long theaterId;
    private String name;
    private String location;
    private Date openingDate;
    private int totalSeatingCapacity;
    private List<TheaterRoom> rooms;
}
