package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.Mapper.ProductMapper;
import com.example.cinemachainmanagement.entities.*;
import com.example.cinemachainmanagement.enums.EMethod;
import com.example.cinemachainmanagement.repositories.SnackOrderRepository;
import com.example.cinemachainmanagement.repositories.TransactionRepository;
import com.example.cinemachainmanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.util.Date;


@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    SnackOrderRepository snackOrderRepository;

    @Override
    public Transaction addTransaction(Transaction transaction, Customer customer, Orders orders, String paymentType, String total_price) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        EMethod methodType = EMethod.valueOf(paymentType);

        transaction.setMethodType(methodType);
        transaction.setCustomer(customer);
        transaction.setOrders(orders);
        transaction.setTotalAmount(Integer.parseInt(total_price));
        transaction.setTransactionStatus(true);
        transaction.setCreated(date);
        //cập nhật lại Order
        orders.setOrderStatus(true);
        snackOrderRepository.save(orders);

        // lưu transaction
        transactionRepository.save(transaction);
        return  transaction;
    }
}
