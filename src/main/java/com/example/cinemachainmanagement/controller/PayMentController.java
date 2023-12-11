package com.example.cinemachainmanagement.controller;

import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.PaymentMethod;
import com.example.cinemachainmanagement.entities.Transaction;
import com.example.cinemachainmanagement.enums.EMethod;
import com.example.cinemachainmanagement.repositories.CustomerRepository;
import com.example.cinemachainmanagement.repositories.PaymentMethodRepository;
import com.example.cinemachainmanagement.service.PaymentMenthodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class PayMentController {
    @Autowired
    PaymentMenthodService paymentMenthodService;
    @PostMapping("payment")
    public String payMent(@RequestParam("paymentType") String paymentType,
                          @RequestParam("total_price") String total_price, HttpSession session) {
        try {


            PaymentMethod paymentMethod = new PaymentMethod();
            EMethod methodType = EMethod.valueOf(paymentType);
            paymentMethod.setMethodType(methodType);

            Customer customer = (Customer)session.getAttribute("customer");
            paymentMenthodService.payment(paymentMethod, customer);




        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
            return "error_view";
        }
        return "success";
    }
}
