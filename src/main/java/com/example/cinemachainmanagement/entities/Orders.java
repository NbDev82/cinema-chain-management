package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SnackOrders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snack_order_id")
    private Long snackOrderId;

    @Column(name = "total_prices")
    private double total_prices;

    @OneToMany(mappedBy = "orders", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<ShoppingCartItem> shoppingCartItems;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Ticket> tickets;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Transaction> transactions ;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
