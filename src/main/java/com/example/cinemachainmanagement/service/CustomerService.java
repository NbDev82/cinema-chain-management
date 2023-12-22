package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.enums.EMessage;

public interface CustomerService {
    Customer authenticateCustomer(String email, String password);
    boolean registerCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Long customerDTO_id);
    EMessage changePassword(String email, String oldPassword, String newPassword);

    EMessage changePassword(String email,String newPassword);
    Customer getCustomerByEmail(String email);
    Customer recharge(Long cusId, String denominations);
}
