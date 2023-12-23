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
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
                           @RequestParam("rating")String rating,
                           @RequestParam("image")String productImage){

        switch (rating){
            case "1":
                rating = "LOW";
                break;
            case "2":
                rating = "BELOW_AVERAGE";
                break;
            case "3":
                rating = "AVERAGE";
                break;
            case "4":
                rating = "ABOVE_AVERAGE";
                break;
            case "5":
                rating = "HIGH";
                break;
            default:
                System.out.println("Lỗi");
                break;
        }
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
            try {
                // Lưu hình ảnh vào thư mục
                //Path path = Paths.get("src/main/resources/static/");
                //InputStream inputStream = image_multipart.getInputStream();
                //Files.copy(inputStream, path.resolve(image_multipart.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

                MovieDTO movieDTO = new MovieDTO(title, description, parsedDate, duration, genre, selectedRating,productImage);
                theaterService.addMovieToTheater(movieDTO, theater);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("loix");
                return "error_view";
            }
        }
        else {
            System.out.println("khong ton tai");
            return "error_view";
        }
        return "redirect:/admin/dashboard-"+theaterName;
    }



    @PostMapping(value = "/delete_movie-{theaterName}")
    public String deleteMovie(@PathVariable String theaterName,
                                @RequestParam(name = "movie_id")String movieId){
        MovieDTO movie = movieService.findByMovieId(movieId);
        System.out.println(movie.getMovieId());

        Optional<Theater> theaterOptional = theaterService.getTheaterByTheaterName(theaterName);
        if(theaterOptional.isPresent()){
            Theater theater = theaterOptional.get();
            theaterService.deleteMovie(movie, theater);
        }else
            return "error_view";

        return "redirect:/admin/dashboard-"+theaterName +"/get_list_movie";
        // "redirect:/admin/dashboard-"+theaterName;
    }
}

