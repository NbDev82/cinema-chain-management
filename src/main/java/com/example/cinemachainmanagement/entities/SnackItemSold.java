package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "SnackItemsSold")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnackItemSold implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snack_item_sold_id")
    private Long snackItemSoldId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_quantity")
    private int itemQuantity;

    @Column(name = "item_price")
    private int itemPrice;

    @ManyToOne
    @JoinColumn(name = "snack_order_id")
    private SnackOrder snackOrder;
}
