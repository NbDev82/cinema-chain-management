package com.example.cinemachainmanagement.controller;


import com.example.cinemachainmanagement.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final CustomerService customerService;

    public HomeController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/theater")
    public String theater(){
        return "views/Theater";
    }
    @GetMapping("/header")
    public  String main(){
        return "views/header";
    }
    @GetMapping("/login")
    public String login(){
        return "views/login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "views/signup";
    }
}
