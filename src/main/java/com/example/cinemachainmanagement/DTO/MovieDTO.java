package com.example.cinemachainmanagement.DTO;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO {
    private Long movieId;
    private String title;
    private String description;
    private Date releaseDate;
    private int duration;
    private String genre;
}
