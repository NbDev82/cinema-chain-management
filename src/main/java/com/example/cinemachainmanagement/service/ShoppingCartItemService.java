package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.ShoppingCartItemDTO;
import com.example.cinemachainmanagement.entities.ShoppingCartItem;

public interface ShoppingCartItemService {
    void addShoppingCartItem(ShoppingCartItemDTO shoppingCartItem, Long snackOrderId, Long productId);
}
