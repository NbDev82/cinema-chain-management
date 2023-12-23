package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.Orders;
import com.example.cinemachainmanagement.entities.Ticket;

import java.util.List;

public interface SnackOrderService {
    List<ProductDTO> getListProductInCart(List<ProductDTO> productDTOS);
    void addSnackOrder(Orders orders, int price, List<Ticket> ticketList, List<ProductDTO> dataList, Customer customer);
}
