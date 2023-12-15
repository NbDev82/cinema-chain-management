package com.example.cinemachainmanagement.controller.admin;

import org.springframework.security.access.AccessDeniedException;;
import com.example.cinemachainmanagement.DTO.MovieDTO;
import com.example.cinemachainmanagement.DTO.ShowtimeDTO;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.Theater;
import com.example.cinemachainmanagement.enums.ERole;
import com.example.cinemachainmanagement.service.MovieService;
import com.example.cinemachainmanagement.service.TheaterService;
import com.example.cinemachainmanagement.service.TimeService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class HomeAdminController {
    private final Logger logger = LoggerFactory.getLogger(HomeAdminController.class);
    private final TheaterService theaterService;
    private final TimeService timeService;
    private final MovieService movieService;

    public HomeAdminController(TheaterService theaterService, TimeService timeService, MovieService movieService) {
        this.theaterService = theaterService;
        this.timeService = timeService;
        this.movieService = movieService;
    }

    @GetMapping("/dashboard-{theaterName}")
    public String index(@PathVariable String theaterName,
                        HttpSession session,
                        Model model) {
        String url = "admin";
        Boolean isAdmin = (Boolean)session.getAttribute("isAdmin");

        if (isAdmin == null || !isAdmin) {
            return "403";
        }
        model.addAttribute("theaterName", theaterName);
        model.addAttribute("showShowtime", false);
        return url;
    }

    @PostMapping("/dashboard-{theaterName}")
    public String get(@PathVariable String theaterName,
                      @RequestParam(name = "action") String action,
                      HttpSession session,
                      Model model) {
        String url = "404";
        Boolean isAdmin = (Boolean)session.getAttribute("isAdmin");

        if (isAdmin == null || !isAdmin) {
            return "403";
        }
        if (action.equals("get-list-showtime")) {
            url = "admin";
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer == null)
                customer = new Customer();
            Optional<Theater> theaterOptional = theaterService.getTheaterByTheaterName(theaterName);
            if (theaterOptional.isPresent()) {
                Theater theater = theaterOptional.get();
                List<ShowtimeDTO> showtimes = timeService.getShowTimeByTheater(theater);
                for (ShowtimeDTO s : showtimes) {
                    logger.info(s.getMovie().getTitle());
                }
                model.addAttribute("showShowtime", true);
                model.addAttribute("theaterName", theaterName);
                model.addAttribute("customer", customer);
                model.addAttribute("showtimes", showtimes);
            } else {
                url = "404";
            }
        } else if(action.equals("schedule")){
            url = "admin";
            if(Schedule(theaterName)){
                model.addAttribute("messageSchedule", "Schedule successfully!");
            }else{
                model.addAttribute("messageSchedule", "Schedule failed!");
            }
        }
        session.setAttribute("theaterName", theaterName);
        return url;
    }

    @GetMapping("/dashboard-{theaterName}/get_list_movie")
    public String getListMovie(Model model,
                               @PathVariable String theaterName,
                               HttpSession session) {
        Boolean isAdmin = (Boolean)session.getAttribute("isAdmin");

        if (isAdmin == null || !isAdmin) {
            return "403";
        }
        try {
            List<MovieDTO> movie_manager = movieService.getListMovie();
            model.addAttribute("movie_manager", movie_manager);
            return "admin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi load sản phẩm: " + e.getMessage());
            return "error_view";
        }
    }

    @GetMapping(value = "/dashboard-{theaterName}/add_movie")
    public String get_form(@PathVariable String theaterName,
                           HttpSession session){
        Boolean isAdmin = (Boolean)session.getAttribute("isAdmin");

        if (isAdmin == null || !isAdmin) {
            return "403";
        }
        return "/add_movie";
    }

    private boolean Schedule(String theaterName) {
        Optional<Theater> theaterOptional = theaterService.getTheaterByTheaterName(theaterName);
        if(theaterOptional.isPresent())
            return timeService.scheduleShowTime(theaterOptional.get());
        return false;
    }
}

