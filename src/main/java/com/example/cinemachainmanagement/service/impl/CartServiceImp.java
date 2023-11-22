package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.model.CartItem;
import com.example.cinemachainmanagement.repositories.ProductCategoryRepository;
import com.example.cinemachainmanagement.service.CartService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartServiceImp implements CartService {

    Map<Long, CartItem> maps = new HashMap<>();//gio hang

    @Override
    public void add(CartItem item) {

        CartItem cartItem = maps.get(item.getProductId());
        if (cartItem == null) {
            maps.put(item.getProductId(), item);
        } else {
            cartItem.setQty(cartItem.getQty()+item.getQty());
        }
    }

    @Override
    public void remove(Long id) {
        maps.remove(id);
    }

    @Override
    public CartItem update(Long proID, int qty) {

        CartItem cartItem = maps.get(proID);

        cartItem.setQty(qty);

        return cartItem;
    }

    @Override
    public void clear() {
        maps.clear();
    }

    @Override
    public Collection<CartItem> getAllItem() {
        return maps.values();
    }

    @Override
    public int totalPrice(Collection<CartItem> listCart)
    {
        int total_price = 0;
        for(CartItem cart_item : listCart ){
            total_price += cart_item.getQty()*cart_item.getPrice();
        }
        return total_price;
    }


}

