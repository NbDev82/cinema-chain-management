package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.Mapper.ProductMapper;
import com.example.cinemachainmanagement.entities.*;
import com.example.cinemachainmanagement.repositories.ProductRepository;
import com.example.cinemachainmanagement.repositories.ShoppingCartItemRepository;
import com.example.cinemachainmanagement.repositories.SnackOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceItemImp implements ShoppingCartItemService  {
    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;
    @Autowired
    SnackOrderRepository snackOrderRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public void addShoppingCartItem(ShoppingCartItem shoppingCartItem, Long snackOrderId, Long productId) {
        try {
            SnackOrder snackOrder = snackOrderRepository.findById(snackOrderId).orElse(null);
            Product product = productRepository.findById(productId).orElse(null);
            if (snackOrder != null && product!=null) {
                shoppingCartItem.setSnackOrder(snackOrder);
                shoppingCartItem.setProduct(product);
                shoppingCartItemRepository.save(shoppingCartItem);
            } else {
                System.out.println("Error");
                return;
            }
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }
}
