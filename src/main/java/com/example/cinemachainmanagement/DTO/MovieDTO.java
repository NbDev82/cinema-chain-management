package com.example.cinemachainmanagement.DTO;

import com.example.cinemachainmanagement.enums.ERating;
import jakarta.persistence.Column;
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
    private ERating rating;
    private String productImage;


    public MovieDTO(String title, String description, Date releaseDate, int duration, String genre, ERating rating,String productImage) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.genre = genre;
        this.rating = rating;
        this.productImage = productImage;
    }
}
