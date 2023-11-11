package com.example.cinemachainmanagement.controller;

import com.example.cinemachainmanagement.DTO.*;
import com.example.cinemachainmanagement.entities.*;
import com.example.cinemachainmanagement.mapper.Mapper;
import com.example.cinemachainmanagement.service.TheaterService;
import com.example.cinemachainmanagement.service.TimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.cinemachainmanagement.entities.Movie;
import com.example.cinemachainmanagement.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dat-ve")
public class BookTicketController {
    private final Logger logger = LoggerFactory.getLogger(BookTicketController.class);
    private final Mapper mapper;
    private final MovieService movieService;
    private final TimeService timeService;
    private final TheaterService theaterService;

    public BookTicketController(MovieService movieService, TimeService timeService, TheaterService theaterService, Mapper mapper) {
        this.movieService = movieService;
        this.timeService = timeService;
        this.theaterService = theaterService;
        this.mapper = mapper;
    }

    @GetMapping("/{movieName}")
    public String getMoviePage(@PathVariable String movieName,
                               @RequestParam(value ="citySelectConfig", defaultValue = "Cả nước") String selectedCity,
                               @RequestParam(value = "movieTimeConfig", defaultValue = "") String selectedMovieTime,
                               @RequestParam(value ="theaterSelectConfig", defaultValue = "Tất cả rạp") String selectedTheater,
                               Model model){
        List<Theater> theatersHasMovie = new ArrayList<>();
        Movie movie = movieService.findMovieByName(movieName);
        if(movie != null){
            List<Showtime> showtimes = timeService.findByMovie(movie);
            if(!selectedMovieTime.isEmpty()){
                showtimes = timeService.filterBySelectedTime(showtimes, selectedMovieTime);
                if(!showtimes.isEmpty()){
                    theatersHasMovie = theaterService.filterTheatersByRoom(showtimes);
                    if(!selectedTheater.equals("Tất cả rạp") && theatersHasMovie != null){
                        logger.info("in filterTheatersByTheaterName");
                        theatersHasMovie = theaterService.filterTheatersByTheaterName(theatersHasMovie,selectedTheater);
                    }
                    if(!selectedCity.equals("Cả nước") && theatersHasMovie != null){
                        logger.info("in filterTheatersByLocation");
                        theatersHasMovie = theaterService.filterTheatersByLocation(theatersHasMovie,selectedCity);
                    }
                }
            }
            List<MovieDTO> movieIsShowing = mapper.mapperEntityToDto(movieService.findShowingMovie(), MovieDTO.class);
            model.addAttribute("movieIsShowing",movieIsShowing);
            model.addAttribute("movie",movie);
            model.addAttribute("theaters",theatersHasMovie);
            model.addAttribute("citySelectConfig",selectedCity);
            model.addAttribute("movieTimeConfig",selectedMovieTime);
            model.addAttribute("theaterSelectConfig",selectedTheater);
        }else{
            logger.error("name movie not exist!");
            return "views/404.html";
        }
        return "views/bookTickets";
    }
}
