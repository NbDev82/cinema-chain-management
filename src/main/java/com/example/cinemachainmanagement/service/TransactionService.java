package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.Orders;
import com.example.cinemachainmanagement.entities.Transaction;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction, Customer customer, Orders orders, String paymentType,String total_price);
}
