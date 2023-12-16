package com.example.cinemachainmanagement.repositories;

import com.example.cinemachainmanagement.entities.Orders;
import com.example.cinemachainmanagement.entities.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    List<ShoppingCartItem> findByOrders(Orders orders);
}
