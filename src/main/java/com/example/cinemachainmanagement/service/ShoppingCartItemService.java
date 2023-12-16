package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.ShoppingCartItemDTO;
import com.example.cinemachainmanagement.entities.Orders;
import com.example.cinemachainmanagement.entities.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartItemService {
    void addShoppingCartItem(ShoppingCartItemDTO shoppingCartItem, Long snackOrderId, Long productId);
    List<ShoppingCartItem> findByOrders(Orders orders);
}
