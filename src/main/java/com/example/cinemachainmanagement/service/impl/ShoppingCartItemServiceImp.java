package com.example.cinemachainmanagement.service.impl;
import com.example.cinemachainmanagement.DTO.ShoppingCartItemDTO;
import com.example.cinemachainmanagement.Mapper.Mappers;
import com.example.cinemachainmanagement.entities.*;
import com.example.cinemachainmanagement.repositories.ProductRepository;
import com.example.cinemachainmanagement.repositories.ShoppingCartItemRepository;
import com.example.cinemachainmanagement.repositories.SnackOrderRepository;
import com.example.cinemachainmanagement.service.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartItemServiceImp implements ShoppingCartItemService {
    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;
    @Autowired
    SnackOrderRepository snackOrderRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public void addShoppingCartItem(ShoppingCartItemDTO shoppingCartItemDTO, Long snackOrderId, Long productId) {
        try {
            ShoppingCartItem shoppingCartItem = Mappers.convertToEntity(shoppingCartItemDTO, ShoppingCartItem.class);

            Orders orders = snackOrderRepository.findById(snackOrderId).orElse(null);
            Product product = productRepository.findById(productId).orElse(null);
            if (orders != null && product!=null) {
                shoppingCartItem.setOrders(orders);
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

    @Override
    public List<ShoppingCartItem> findByOrders(Orders orders){
        return shoppingCartItemRepository.findByOrders(orders);
    }

}
