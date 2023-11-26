package com.example.cinemachainmanagement.controller.admin;

import com.example.cinemachainmanagement.entities.Theater;
import com.example.cinemachainmanagement.service.TheaterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.example.cinemachainmanagement.DTO.MovieDTO;
import com.example.cinemachainmanagement.enums.ERating;
import com.example.cinemachainmanagement.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movie_management")
public class ManagerMovieController {
    @Autowired
    MovieService movieService;

    @Autowired
    TheaterService theaterService;

    @PostMapping(value = "/add_movie-{theaterName}")
    public String addMovie(@PathVariable String theaterName,
                           @RequestParam("title")String title,
                           @RequestParam("description")String description,
                           @RequestParam("releaseDate")String releaseDate,
                           @RequestParam("duration")int duration,
                           @RequestParam("genre")String genre,
                           @RequestParam("rating")String rating){

        Optional<Theater> theaterOptional = theaterService.getTheaterByTheaterName(theaterName);
        if(theaterOptional.isPresent()){
            Theater theater = theaterOptional.get();

            ERating selectedRating =  ERating.valueOf(rating);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = null;
            try {
                parsedDate = dateFormat.parse(releaseDate);
            } catch (ParseException e) {
                e.printStackTrace(); // Xử lý exception nếu cần thiết
                return "error_view";
            }
            MovieDTO movieDTO = new MovieDTO(title, description, parsedDate, duration, genre, selectedRating);
            theaterService.addMovieToTheater(movieDTO, theater);
        }
        else {
            return "error_view";
        }
        return "success";

    }

    @GetMapping(value = "/add_movie")
    public String get_form(){
        return "/add_movie";
    }


    @GetMapping("/get_list_movie")
    public String getListMovie(Model model) {
        try {
            List<MovieDTO> movie_manager = movieService.getListMovie();
            model.addAttribute("movie_manager", movie_manager);
            return "admin";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi load sản phẩm: " + e.getMessage());
            return "error_view";
        }
    }

    @GetMapping(value = "/delete_movie")
    public String deleteMovie(@RequestParam(name = "movieId")String movieId){
        System.out.println(movieId);
        //movieService.deleteMovie(id);
        return "success";
    }
}

