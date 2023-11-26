package com.example.cinemachainmanagement.controller.admin;

import com.example.cinemachainmanagement.DTO.ShowtimeDTO;
import com.example.cinemachainmanagement.controller.BookTicketController;
import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.Theater;
import com.example.cinemachainmanagement.service.TheaterService;
import com.example.cinemachainmanagement.service.TimeService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class CalendarController {
    private final Logger logger = LoggerFactory.getLogger(CalendarController.class);
    private final TheaterService theaterService;
    private final TimeService timeService;

    public CalendarController(TheaterService theaterService, TimeService timeService) {
        this.theaterService = theaterService;
        this.timeService = timeService;
    }

    @GetMapping("/dashboard-{theaterName}")
    public String index(@PathVariable String theaterName,
                        HttpSession session,
                        Model model) {
        String url = "admin";
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
        return url;
    }

    private boolean Schedule(String theaterName) {
        Optional<Theater> theaterOptional = theaterService.getTheaterByTheaterName(theaterName);
        if(theaterOptional.isPresent())
            return timeService.scheduleShowTime(theaterOptional.get());
        return false;
    }
}

