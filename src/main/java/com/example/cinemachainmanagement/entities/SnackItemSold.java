package com.example.cinemachainmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SnackItemsSold")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnackItemSold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snack_item_sold_id")
    private int snackItemSoldId;

    @ManyToOne
    @JoinColumn(name = "snack_order_id")
    private SnackOrder snackOrder;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_quantity")
    private int itemQuantity;
}
