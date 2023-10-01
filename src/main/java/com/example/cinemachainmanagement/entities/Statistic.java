package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Statistics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistic_id")
    private int statisticId;

    @Column(name = "total_tickets_sold")
    private int totalTicketsSold;

    @Column(name = "total_revenue")
    private double totalRevenue;

    @Column(name = "date")
    private Date date;
}

