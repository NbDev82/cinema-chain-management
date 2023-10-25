package com.example.cinemachainmanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.sql.Timestamp;

@Embeddable
public class DetailPaymentMethod {
    @Column(name = "card_number")
    private String numberOfCard;

    @Column(name = "security_code")
    private String securityCode;

    @Column(name = "expiration_date")
    private Timestamp expirationDate;
}
