package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.Mapper.Mappers;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.SnackOrder;
import com.example.cinemachainmanagement.entities.Ticket;
import com.example.cinemachainmanagement.repositories.SnackOrderRepository;
import com.example.cinemachainmanagement.service.SnackOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnackOrderServiceImp implements SnackOrderService {
    @Autowired
    SnackOrderRepository snackOrderRepository;

    @Override
    public void addSnackOrder(SnackOrder snackOrder, int selectPriceProduct, List<Ticket> tickets){
        try {

            snackOrder.setTotal_prices(selectPriceProduct);
            snackOrder.setTickets(tickets);
            snackOrderRepository.save(snackOrder);
        }
        catch (Exception e){
            System.out.println("Error");
        }

    }


}
