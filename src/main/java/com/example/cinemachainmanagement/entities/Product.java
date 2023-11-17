package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_quantity")
    private int productQuantity;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_image")
    private String productImage;  // Đây có thể là URL hoặc đường dẫn tới hình ảnh

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<ShoppingCartItem> shoppingCartItems;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory product_category;

}
