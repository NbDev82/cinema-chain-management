package com.example.cinemachainmanagement.controller;

import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.Ticket;
import com.example.cinemachainmanagement.service.TicketService;
import com.example.cinemachainmanagement.service.impl.TicketServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/history")
public class TransactionHistoryController {
    @Autowired
    TicketService ticketService;
    @GetMapping("")
    public String getHistory(Model model, HttpSession session){
        Customer customer= (Customer) session.getAttribute("customer");
        List<Ticket> history = ticketService.findAllByCustomer(customer);
        model.addAttribute("history",history);
        return "transactionHistory";
    }
}
