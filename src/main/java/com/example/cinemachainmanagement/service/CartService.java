package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.model.CartItem;

import java.util.Collection;

public interface CartService {
    void add(CartItem item);

    void remove(Long id);

    CartItem update(Long proID, int qty);

    void clear();

    Collection<CartItem> getAllItem();
    int totalPrice(Collection<CartItem> listCart);
}
