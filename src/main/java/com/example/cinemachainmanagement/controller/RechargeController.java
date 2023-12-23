package com.example.cinemachainmanagement.controller;

import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.RecursiveTask;

@Controller
@RequestMapping("/recharge")
public class RechargeController {
    @Autowired
    CustomerService customerService;
    @GetMapping("/form")
    public String getRecharge(){
        return "recharge";
    }

    @PostMapping("confirmRecharge")
    public  String confirmRecharge(@RequestParam("denominations") String denominations, HttpSession session){
        Customer customer = (Customer)session.getAttribute("customer");
        if(customer == null){
            return "redirect:/customer_authentication/login";
        }
        Customer updatedCustomerBalance = customerService.recharge(customer.getCustomerId(), denominations);
        session.setAttribute("customer",updatedCustomerBalance);
        return "redirect:/home";
    }
}
