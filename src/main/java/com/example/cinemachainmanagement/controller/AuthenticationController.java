package com.example.cinemachainmanagement.controller;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.enums.EMessage;
import com.example.cinemachainmanagement.enums.ERole;
import com.example.cinemachainmanagement.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer_authentication")
public class AuthenticationController {
    @Autowired
    CustomerService  customerService;
    @GetMapping("/login")
    public String Login(Model model) {
        try {
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "error_view";
        }
    }

    @PostMapping("/login")
    public String checkLogin(@RequestParam(name = "email") String email, @RequestParam(name = "password")String password, Model model, HttpSession session) {
        String url = (String)session.getAttribute("url");
        if(url == null || url.isEmpty()){
            url = "redirect:/home";
            session.setAttribute("url",url);
        }
        Customer customer = customerService.authenticateCustomer(email,password);
        if(customer != null){
            session.setAttribute("customer",customer);
            if(customer.getRole() == ERole.ADMIN){
                session.setAttribute("isAdmin", true);
            } else {
                session.setAttribute("isAdmin", false);
            }
            return url;
        } else {
            model.addAttribute("error", "Email hoặc mật khẩu không chính xác");
            return "error_view";
        }
    }
    @GetMapping("/register")
    public String Register(Model model) {
        try {
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng kí: " + e.getMessage());
            return "error_view";
        }
    }

    @PostMapping("/register")
    public String checkRegister(@ModelAttribute CustomerDTO customerDTO, Model model) {
        try {
            if(customerService.registerCustomer(customerDTO)){
                return "redirect:/customer_authentication/login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng kí: " + e.getMessage());
            return "error_view";
        }
        return "error_view";
    }

    @GetMapping("/logout")
    private String logout(HttpSession session){
        session.setAttribute("customer", null);
        System.out.println(session.getAttribute("customer"));
        return "redirect:/customer_authentication/login";
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model){
        try{
            return "passwordResult";
        } catch(Exception e){
            model.addAttribute("error", "Lỗi đổi mật khẩu: " + e.getMessage());
            return "error_view";
        }
    }
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword, Model model) {
        EMessage result = customerService.changePassword(email, oldPassword, newPassword);
        model.addAttribute("result", result.getValue());
        return "passwordResult";
    }
}
