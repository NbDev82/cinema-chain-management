package com.example.cinemachainmanagement.entities;

import java.io.Serializable;
import java.util.List;

import com.example.cinemachainmanagement.enums.EMethod;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment_methods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private Long paymentMethodId;

    @Column(name = "method_type")
    @Enumerated(EnumType.STRING)
    private EMethod methodType;

    @Embedded
    private DetailPaymentMethod details;

    @OneToMany(mappedBy = "paymentMethod", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @ManyToMany(mappedBy = "paymentMethods")
    private List<Customer> customers;
}
