package com.example.cinemachainmanagement.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDTO implements Comparable<SeatDTO> {
    private Long seatId;
    private String seatNumber;
    private boolean isReserved;


    @Override
    public int compareTo(SeatDTO o) {
        return Integer.compare(Integer.parseInt(o.seatNumber), Integer.parseInt(this.seatNumber));
    }
}
