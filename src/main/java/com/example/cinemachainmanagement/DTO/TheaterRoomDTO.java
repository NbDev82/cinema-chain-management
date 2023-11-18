package com.example.cinemachainmanagement.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterRoomDTO {
    private Long roomId;
    private int roomNumber;
    private int seatingCapacity;
    private String roomType;
    private Long theaterId;
    private List<SeatDTO> seats; // Assuming you want to include a list of seat IDs
    private TheaterDTO theater;
}
