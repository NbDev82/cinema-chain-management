package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SnackOrders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnackOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snack_order_id")
    private Long snackOrderId;

    @Column(name = "total_prices")
    private double total_prices;

    @OneToMany(mappedBy = "snackOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<ShoppingCartItem> shoppingCartItems;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
