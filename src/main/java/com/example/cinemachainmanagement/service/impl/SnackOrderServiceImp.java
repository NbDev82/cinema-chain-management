package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.Mapper.Mappers;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.SnackOrder;
import com.example.cinemachainmanagement.repositories.SnackOrderRepository;
import com.example.cinemachainmanagement.service.SnackOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnackOrderServiceImp implements SnackOrderService {
    @Autowired
    SnackOrderRepository snackOrderRepository;

    @Override
    public void addSnackOrder(SnackOrder snackOrder, int total_price, CustomerDTO customerDTO){
        try {
            Customer customer = Mappers.convertToEntity(customerDTO, Customer.class);
            snackOrder.setTotal_prices(total_price);
//            snackOrder.setCustomer(customer);
            snackOrderRepository.save(snackOrder);
        }
        catch (Exception e){
            System.out.println("Error");
        }

    }
}
