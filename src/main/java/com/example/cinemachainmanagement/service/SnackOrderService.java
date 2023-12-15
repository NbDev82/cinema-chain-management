package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.SnackOrder;
import com.example.cinemachainmanagement.entities.Ticket;

import java.util.List;

public interface SnackOrderService {
    void addSnackOrder(SnackOrder snackOrder, int selectPriceProduct, List<Ticket> ticket, List<ProductDTO> dataList);
}
