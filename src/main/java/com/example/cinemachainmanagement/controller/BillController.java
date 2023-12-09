package com.example.cinemachainmanagement.controller;

import com.example.cinemachainmanagement.entities.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bill")
public class BillController {
    @GetMapping("")
    public String getBill(Model model){
        Ticket bill = new Ticket();
        model.addAttribute("bill", bill);
        return "bill";
    }

}
