package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.PaymentMethod;

public interface PaymentMenthodService {
    void payment(PaymentMethod paymentMethod, Customer customer);
}
