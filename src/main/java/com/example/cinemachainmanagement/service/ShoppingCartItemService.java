package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.entities.ShoppingCartItem;

public interface ShoppingCartItemService {
    void addShoppingCartItem(ShoppingCartItem shoppingCartItem, Long snackOrderId, Long productId);
}
