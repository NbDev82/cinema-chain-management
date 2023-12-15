package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.DTO.ProductDTO;
import com.example.cinemachainmanagement.entities.*;
import com.example.cinemachainmanagement.repositories.ProductRepository;
import com.example.cinemachainmanagement.repositories.ShoppingCartItemRepository;
import com.example.cinemachainmanagement.repositories.SnackOrderRepository;
import com.example.cinemachainmanagement.service.ProductService;
import com.example.cinemachainmanagement.service.SnackOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SnackOrderServiceImp implements SnackOrderService {
    @Autowired
    SnackOrderRepository snackOrderRepository;

    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Override
    public void addSnackOrder(Orders orders, int selectPriceProduct, List<Ticket> tickets, List<ProductDTO> dataListProductBuy) {
        try {
            //chuyển thanh list các ShoppingCartItem
            List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();
            for (ProductDTO productDTO : dataListProductBuy) {
                ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                shoppingCartItem.setShoppingCartItemQuantity(String.valueOf(productDTO.getQuantity()));
                shoppingCartItem.setOrders(orders);

                Product productDTO1 = productService.getProductByIdEntity(String.valueOf(productDTO.getId()));
                shoppingCartItem.setProduct(productDTO1);
                shoppingCartItems.add(shoppingCartItem);
            }

            orders.setTotal_prices(selectPriceProduct);
            orders.setShoppingCartItems(shoppingCartItems);
            orders.setTickets(null);
            snackOrderRepository.save(orders);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Erro r");
        }

    }


}
