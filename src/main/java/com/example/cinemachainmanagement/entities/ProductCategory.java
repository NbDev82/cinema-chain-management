package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ProductCategory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private Long productCategoryID;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "product_category",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Product> products;
}
