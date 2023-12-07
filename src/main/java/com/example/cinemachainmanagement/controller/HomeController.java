package com.example.cinemachainmanagement.controller;


import com.example.cinemachainmanagement.DTO.MovieDTO;
import com.example.cinemachainmanagement.service.CustomerService;
import com.example.cinemachainmanagement.service.MovieService;
import com.example.cinemachainmanagement.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final CustomerService customerService;

    @Autowired
    MovieService movieService;

    @Autowired
    TheaterService theaterService;

    public HomeController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/theater")
    public String theater(){
        return "views/Theater";
    }
    @GetMapping("/header")
    public  String main(){
        return "header";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }


    @GetMapping()
    public String getListMovie(Model model) {
        try {
            List<MovieDTO> movie_manager = movieService.getListMovie();
            model.addAttribute("movie_manager", movie_manager);
            return "home";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi load phim" + e.getMessage());
            return "error_view";
        }
    }

//    @GetMapping("list_show_time")
//    public String getListShowTime(Model model) {
//        try {
//            List<MovieDTO> movie_manager = movieService.getListMovie();
//            model.addAttribute("movie_manager", movie_manager);
//            return "home";
//        } catch (Exception e) {
//            model.addAttribute("error", "Lỗi khi load phim" + e.getMessage());
//            return "error_view";
//        }
//    }
}
