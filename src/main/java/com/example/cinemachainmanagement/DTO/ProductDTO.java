package com.example.cinemachainmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private int price;
    private String image;
    private int quantity;
    private Long productCategoryId;

    public ProductDTO(Long id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDTO(String name, String description, int price, String image, int quantity, Long productCategoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.productCategoryId = productCategoryId;
    }
}
