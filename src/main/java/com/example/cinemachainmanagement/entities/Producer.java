package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "producers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producer_id")
    private Long producerId;

    @Column(name = "producer_name")
    private String producerName;

    @ManyToMany(mappedBy = "producers")
    private List<Movie> movies;
}
