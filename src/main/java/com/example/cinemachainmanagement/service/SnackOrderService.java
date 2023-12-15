package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.entities.Orders;
import com.example.cinemachainmanagement.entities.Ticket;

import java.util.List;

public interface SnackOrderService {
    void addSnackOrder(Orders orders, int selectPriceProduct, List<Ticket> ticket, List<ProductDTO> dataList);
}
