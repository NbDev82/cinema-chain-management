package com.example.cinemachainmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
    private int quantity;

    public ProductDTO(String name, String description, double price, String image, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }


}
