package com.example.cinemachainmanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.cinemachainmanagement.DTO.*;
import com.example.cinemachainmanagement.entities.*;
import com.example.cinemachainmanagement.Mapper.Mapper;
import com.example.cinemachainmanagement.service.TheaterService;
import com.example.cinemachainmanagement.service.TicketService;
import com.example.cinemachainmanagement.service.TimeService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.*;
import com.example.cinemachainmanagement.entities.Movie;
import com.example.cinemachainmanagement.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/dat-ve")
public class BookTicketController{
    private final Logger logger = LoggerFactory.getLogger(BookTicketController.class);
    private final Mapper mapper;
    private final MovieService movieService;
    private final TimeService timeService;
    private final TheaterService theaterService;
    private final TicketService ticketService;


    public BookTicketController(MovieService movieService, TimeService timeService, TheaterService theaterService, Mapper mapper, TicketService ticketService) {
        this.movieService = movieService;
        this.timeService = timeService;
        this.theaterService = theaterService;
        this.mapper = mapper;
        this.ticketService = ticketService;
    }

    @GetMapping("/{movieName}")
    public String getMoviePage(HttpSession session,
                               @PathVariable String movieName,
                               @RequestParam(value ="citySelectConfig", defaultValue = "Cả nước") String selectedCity,
                               @RequestParam(value = "movieTimeConfig", defaultValue = "") String selectedMovieTime,
                               @RequestParam(value ="theaterSelectConfig", defaultValue = "Tất cả rạp") String selectedTheater,
                               Model model){
        String url = "view/bookTickets";
        List<Theater> theatersHasMovie = new ArrayList<>();
        Movie movie = movieService.findMovieByName(movieName);
        if(movie != null){
            List<Showtime> showtimes = timeService.findByMovie(movie);
            if(!selectedMovieTime.isEmpty()){
                showtimes = timeService.filterBySelectedTime(showtimes, selectedMovieTime);
                if(!showtimes.isEmpty()){
                    theatersHasMovie = theaterService.filterTheatersByRoom(showtimes);
                    if(!selectedTheater.equals("Tất cả rạp") && theatersHasMovie != null){
                        theatersHasMovie = theaterService.filterTheatersByTheaterName(theatersHasMovie,selectedTheater);
                    }
                    if(!selectedCity.equals("Cả nước") && theatersHasMovie != null){
                        theatersHasMovie = theaterService.filterTheatersByLocation(theatersHasMovie,selectedCity);
                    }
                }
            }
            if(theatersHasMovie == null)
                theatersHasMovie = new ArrayList<>();
            List<TheaterDTO> theaters = mapper.mapperEntityToDto(theatersHasMovie,TheaterDTO.class);
            List<MovieDTO> movieIsShowing = mapper.mapperEntityToDto(movieService.findShowingMovie(), MovieDTO.class);
            model.addAttribute("movieIsShowing",movieIsShowing);
            model.addAttribute("movie",movie);
            model.addAttribute("theaters",theaters);
            model.addAttribute("citySelectConfig",selectedCity);
            model.addAttribute("movieTimeConfig",selectedMovieTime);
            model.addAttribute("theaterSelectConfig",selectedTheater);
        }else{
            url = "404";
        }
        return url;
    }

    @GetMapping("/dat-cho")
    public String getRoomView(HttpSession session,
                              @RequestParam String roomId,
                              @RequestParam String timeId,
                              Model model){
        String url = "view/seat-booking";
        Customer customer = (Customer)session.getAttribute("customer");
        if(customer == null){
            session.setAttribute("url","redirect:/dat-ve/dat-cho?roomId="+roomId+"&timeId="+timeId);
            return "redirect:/customer_authentication/login";
        }
        Optional<TheaterRoom> optionalRoom = theaterService.getTheaterRoomById(Long.valueOf(roomId));
        if (optionalRoom.isPresent()) {
            Optional<Showtime> optionalTime = timeService.findById(Long.valueOf(timeId));
            Showtime time =null;
            if(optionalTime.isPresent()){
                time = optionalTime.get();
                Ticket ticket = Ticket.builder()
                        .ticketStatus(false)
                        .showTime(time)
                        .build();
                List<Ticket> tickets = new ArrayList<>();

                TheaterRoom theaterRoom = optionalRoom.get();
                TheaterRoomDTO roomDTO = ticketService.getOrderSeatsByRoomAndTime(theaterRoom, time.getStartTime());

                Collections.sort(roomDTO.getSeats());
                TicketDTO ticketDTO = mapper.mapEntityToCustomDto(ticket);

                session.setAttribute("tickets", tickets);
                session.setAttribute("time", time);

                model.addAttribute("room", roomDTO);
                model.addAttribute("ticket", ticketDTO);
            }

        }
        return url;
    }

    @PostMapping(value = "/submit-seats")
    public String submitSeats(@RequestParam("selectedSeats") String selectedSeats,
                              HttpSession session,
                              Model model,
                              @RequestParam("price1")String price) throws JsonProcessingException, SystemException {

        AtomicReference<String> url = new AtomicReference<>("redirect:/customer/get_list_product");

        Showtime time = (Showtime)session.getAttribute("time");
        ObjectMapper objectMapper = new ObjectMapper();
        String[] selectedSeatListId = objectMapper.readValue(selectedSeats, String[].class);
        List<Ticket> tickets = ticketService.createTicketsBySeatListId(selectedSeatListId, time);
        if(tickets.isEmpty())
            url.set("error");
        List<TicketDTO> ticketDTOs = mapper.mapperEntityToDto(tickets, TicketDTO.class);
        session.setAttribute("tickets",tickets);
        model.addAttribute("tickets", ticketDTOs);

        session.setAttribute("price",price);
        return url.get();
    }

}


