package com.example.cinemachainmanagement.controller;

import com.example.cinemachainmanagement.DTO.TheaterRoomDTO;
import com.example.cinemachainmanagement.DTO.TicketDTO;
import com.example.cinemachainmanagement.entities.*;
import com.example.cinemachainmanagement.enums.EMethod;
import com.example.cinemachainmanagement.service.*;
import com.mysql.cj.Session;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class PayMentController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    TheaterService theaterService;
    @Autowired
    TimeService timeService;
    @Autowired
    TicketService ticketService;
    @Autowired
    ShoppingCartItemService shoppingCartItemService;

    @PostMapping("payment")
    public String payMent(@RequestParam("paymentType") String paymentType,
                          @RequestParam("total_price") String total_price, HttpSession session,
                          Model model) {
        try {

            Transaction getTransaction = new Transaction();

            Customer customer = (Customer) session.getAttribute("customer");
            Orders orders = (Orders) session.getAttribute("orders");
            Transaction transaction = new Transaction();
            switch (paymentType) {
                case "AtTheCounter":
                    getTransaction = transactionService.addTransaction(transaction, customer, orders, paymentType, total_price);
                    break;
                default:
                    if (customer.getAccountBalance() > Integer.parseInt(total_price)) {
                        getTransaction = transactionService.addTransaction(transaction, customer, orders, paymentType, total_price);
                    } else {
                        //form nạp tiền..........
                        return "redirect:/customer_authentication/login";
                    }
                    break;
            }
            model.addAttribute("transaction", getTransaction);
            List<Ticket> tickets =  ticketService.findTicketsByOrders(getTransaction.getOrders());
            List<ShoppingCartItem>  shoppingCartItems = shoppingCartItemService.findByOrders(getTransaction.getOrders());
            model.addAttribute("tickets", tickets);
            model.addAttribute("shoppingCartItems", shoppingCartItems);

            for(Ticket t: tickets){
                System.out.println("List Ticket By Order:");
                System.out.println(t.getSeat().getSeatNumber());
            }

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
            return "error_view";
        }
        return "bill";
    }

}
