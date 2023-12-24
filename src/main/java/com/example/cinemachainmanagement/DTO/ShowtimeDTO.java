package com.example.cinemachainmanagement.DTO;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowtimeDTO {
    private Long showtimeId;
    private Date date;
    private Date startTime;
    private Date endTime;
    private TheaterRoomDTO room;
    private MovieDTO movie;
}
