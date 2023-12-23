package com.example.cinemachainmanagement.DTO;

import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.Showtime;
import com.example.cinemachainmanagement.entities.TheaterRoom;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {
    private Long ticketId;
    private int price;
    private String seatNumber;
    private int roomNumber;
    private String title;
    private String genre;
    private String theaterName;
    private int duration;
    private Date startTime;
    private Date date;
    private Date purchaseTime;
    private boolean ticketStatus;
    private CustomerDTO customer;
    private String productImage;
}
