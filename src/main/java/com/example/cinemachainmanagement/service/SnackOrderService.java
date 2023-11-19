package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.SnackOrder;

public interface SnackOrderService {
    void addSnackOrder(SnackOrder snackOrder, int total_price, CustomerDTO customerDTO);
}
