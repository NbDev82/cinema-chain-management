package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.PaymentMethod;
import com.example.cinemachainmanagement.entities.Transaction;
import com.example.cinemachainmanagement.enums.EMethod;
import com.example.cinemachainmanagement.repositories.PaymentMethodRepository;
import com.example.cinemachainmanagement.service.PaymentMenthodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentMenthodImpl implements PaymentMenthodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Override
    public void payment(PaymentMethod paymentMethod, Customer customer){
        //paymentMethod.setMethodType(EMethod.VISA);

        System.out.println(customer.getFirstName());

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        paymentMethod.setCustomers(customers);

        Transaction transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setPaymentMethod(paymentMethod);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        paymentMethod.setTransactions(transactions);

        paymentMethodRepository.save(paymentMethod);

    }

}
