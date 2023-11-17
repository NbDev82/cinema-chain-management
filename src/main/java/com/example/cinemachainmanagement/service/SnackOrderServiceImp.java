package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.SnackOrder;
import com.example.cinemachainmanagement.repositories.SnackOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnackOrderServiceImp implements SnackOrderService {
    @Autowired
    SnackOrderRepository snackOrderRepository;

    @Override
    public void addSnackOrder(SnackOrder snackOrder){
        try {
            // gán tạm ID của customer để test là 10. merge code rồi mới xử lý thêm
            Customer customer = new Customer(10L, "John", "Doe", "john@example.com", "1234567890", 500, "passwordHash", null, null, null, null);
            snackOrder.setCustomer(customer);
            snackOrderRepository.save(snackOrder);
        }
        catch (Exception e){
            System.out.println("Error");
        }

    }
}
