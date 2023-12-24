package com.example.cinemachainmanagement.controller;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.enums.EMessage;
import com.example.cinemachainmanagement.enums.ERole;
import com.example.cinemachainmanagement.generator.OTPGenerator;
import com.example.cinemachainmanagement.service.AccountService;
import com.example.cinemachainmanagement.service.CustomerService;
import com.example.cinemachainmanagement.service.EmailService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer_authentication")
public class AuthenticationController {
    private final CustomerService  customerService;
    private final AccountService accountService;
    private final EmailService emailService;
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
            session.setAttribute("email",email);
            if(customer.getRole() == ERole.ADMIN){
                session.setAttribute("isAdmin", true);
            } else {
                session.setAttribute("isAdmin", false);
            }
            return url;
        } else {
            model.addAttribute("result", EMessage.OLD_PASS_NOT_MATCH.getValue());
            return "login";
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
        session.setAttribute("isAdmin", null);
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

    @GetMapping("/forgot-pass")
    public String startForgotPass(){
        try{
            return "your-email";
        }catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/forgot-pass-result")
    public String changePassword(@RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 HttpSession session,
                                 Model model) {
        String url = "login";
        if(newPassword.equals(confirmPassword)){
            String email = (String) session.getAttribute("email");
            EMessage result = customerService.changePassword(email, newPassword);
            model.addAttribute("result", result.getValue());
        }else{
            url = "forgot-pass";
            model.addAttribute("result", EMessage.CONFIRM_PASSWORD_NOT_MATCH.getValue());
        }
        return url;
    }

    @PostMapping("/forgot-pass")
    public String changePass(){
        try{
            return "forgot-pass";
        }catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/otp")
    public String sendOTP(@RequestParam String email,
                          HttpSession session,
                          Model model){
        try{
            Customer customer = customerService.getCustomerByEmail(email);
            if(customer == null){
                model.addAttribute("result",EMessage.CUSTOMER_NOT_EXIST.getValue());
                return "your-email";
            }else{
                String OTP = OTPGenerator.generateOTP();
                String OTPHash = accountService.hashPassword(OTP);
                emailService.sendEmail(email,EMessage.TITLE_OTP.getValue(),EMessage.TEXT_EMAIL_OTP.getValue()+OTP);
                session.setAttribute("OTPHash",OTPHash);
                session.setAttribute("email",email);
                return "type-otp";
            }
        }catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/otp")
    public String checkOTP(@RequestParam String otp,
                           HttpSession session,
                           Model model){
        try{
            String url="forgot-pass";
            String OTPHash = (String) session.getAttribute("OTPHash");
            if(OTPHash == null)
                OTPHash = url;
            if(accountService.hashPassword(otp).equals(OTPHash)){
                session.setAttribute("OTPHash",null);
            }
            else{
                model.addAttribute("result",EMessage.OTP_NOT_MATCH.getValue());
                url = "type-otp";
            }
            return url;
        }catch (Exception e){
            return "404";
        }
    }
    @PostMapping("/customer_profile")
    public  String Profile(@RequestParam("customer_id") String customerID,Model model){
        CustomerDTO customer = customerService.getCustomerById(Long.valueOf(customerID));
        model.addAttribute("customer",customer);
        return "customer_profile";
    }

}
