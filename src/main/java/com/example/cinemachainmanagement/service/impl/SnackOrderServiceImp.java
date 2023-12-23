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
import java.util.Optional;

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
    public void addSnackOrder(Orders orders, int price, List<Ticket> ticketList, List<ProductDTO> dataListProductBuy, Customer  customer) {
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

            //Cap nhật lại vé đó thuộc order nao
            List<Ticket> tickets  = new ArrayList<>();
            for (Ticket ticket : ticketList) {
               ticket.setOrders(orders);
                tickets.add(ticket);
            }

            orders.setTotal_prices(price);
            orders.setOrderStatus(false);
            orders.setShoppingCartItems(shoppingCartItems);
            orders.setTickets(tickets);
            orders.setCustomer(customer);
            snackOrderRepository.save(orders);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error");
        }
    }
    @Override
    public List<ProductDTO> getListProductInCart(List<ProductDTO> productDTOS){
        List<ProductDTO> productInCart = new ArrayList<>();
        for(ProductDTO p : productDTOS){
                Optional<Product> product =  productRepository.findById(p.getId());
                if(product!=null){
                    p.setName(product.get().getProductName());
                    productInCart.add(p);
                }
            }
        return productInCart;
    }
}
