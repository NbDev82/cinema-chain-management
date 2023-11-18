package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.entities.Customer;

public interface CustomerService {
    Customer authenticateCustomer(String email, String password);
    boolean registerCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Long customerDTO_id);
}
