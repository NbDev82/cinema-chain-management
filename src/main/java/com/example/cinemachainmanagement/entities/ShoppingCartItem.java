package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "ShoppingCartItem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_item_id")
    private Long shoppingCartItemId;

    @Column(name = "quantity")
    private String shoppingCartItemQuantity;

    @ManyToOne
    @JoinColumn(name = "snack_order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
