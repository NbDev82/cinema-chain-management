package com.example.cinemachainmanagement.service.impl;


import com.example.cinemachainmanagement.DTO.MovieDTO;
import com.example.cinemachainmanagement.Mapper.Mappers;
import com.example.cinemachainmanagement.Mapper.ProductMapper;
import com.example.cinemachainmanagement.controller.BookTicketController;
import com.example.cinemachainmanagement.entities.*;
import com.example.cinemachainmanagement.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.cinemachainmanagement.service.TheaterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TheaterServiceImpl implements TheaterService {
    private final Logger logger = LoggerFactory.getLogger(BookTicketController.class);
    private final TheaterRepository theaterRepo;
    private final TheaterRoomRepository theaterRoomRepo;
    private final SeatRepository seatRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository ShowtimeRepo;


    public TheaterServiceImpl(TheaterRepository theaterRepo, TheaterRoomRepository theaterRoomRepo, SeatRepository seatRepository,MovieRepository movieRepository, ShowtimeRepository ShowtimeRepo) {
        this.theaterRepo = theaterRepo;
        this.theaterRoomRepo = theaterRoomRepo;
        this.seatRepository = seatRepository;
        this.movieRepository = movieRepository;
        this.ShowtimeRepo = ShowtimeRepo;
    }

    @Override
    public List<Theater> filterTheatersByLocation(List<Theater> theaters, String Location) { return theaters.stream().filter(theater -> theater.getLocation().equals(Location)).toList(); }

    @Override
    public List<Theater> filterTheatersByTheaterName(List<Theater> theaters, String selectedTheater) { return theaters.stream().filter(theater -> theater.getName().equals(selectedTheater)).toList(); }

    @Override
    public List<Theater> filterTheatersByRoom(List<Showtime> showtimes) {
        Map<Theater, Map<TheaterRoom, List<Showtime>>> theaterRoomShowtimeMap = new HashMap<>();

        for (Showtime time : showtimes) {
            Theater theater = time.getRoom().getTheater();
            TheaterRoom room = time.getRoom();

            theaterRoomShowtimeMap
                    .computeIfAbsent(theater, k -> new HashMap<>())
                    .computeIfAbsent(room, k -> new ArrayList<>())
                    .add(time);
        }

        // Tạo danh sách Theater với thông tin Showtime được nhóm lại theo Room
        List<Theater> theaters = new ArrayList<>();
        theaterRoomShowtimeMap.forEach((theater, roomShowtimeMap) -> {
            Theater newTheater = new Theater(theater);
            roomShowtimeMap.forEach((room, showtimeList) -> {
                TheaterRoom newRoom = new TheaterRoom(room);
                newRoom.setShowTimes(showtimeList);
                newTheater.getRooms().add(newRoom);
            });
            theaters.add(newTheater);
        });

        return theaters;
    }


    @Override
    public Optional<TheaterRoom> getTheaterRoomById(Long theaterId) {
        return theaterRoomRepo.findById(theaterId);
    }

    @Override
    public Optional<Seat> getSeatById(Long seatId) {
        return seatRepository.findById(seatId);
    }

    @Override
    public Optional<Theater> getTheaterByTheaterName(String theaterName) {
        return theaterRepo.findByName(theaterName);
    }

    @Override
    public void addMovieToTheater(MovieDTO movieDTO, Theater theater){
        Movie movie = Mappers.convertToEntity(movieDTO, Movie.class);
        movie = movieRepository.save(movie);

        List<Movie> movies = theater.getMovies();
        movies.add(movie);

        theater.setMovies(movies);
        theaterRepo.save(theater);

        for (Movie m: movies){
            System.out.println(m.getDescription());
        }

    }

    @Override
    public void deleteMovie(MovieDTO movieDTO, Theater theater){
        List<Movie> movies = theater.getMovies();
        Movie movie = Mappers.convertToEntity(movieDTO, Movie.class);

        movies.removeIf(m -> m.getMovieId().equals(movie.getMovieId()));

        theater.setMovies(movies);
        theaterRepo.save(theater);



        List<Showtime> showtimes =  ShowtimeRepo.findByMovie(movie);

        if(!showtimes.isEmpty()){
            ShowtimeRepo.deleteAll();
            for (Showtime showtime : showtimes) {
                ShowtimeRepo.deleteByMovieId(showtime.getMovie().getMovieId());
                System.out.println(showtime.getMovie().getMovieId());
            }
        }
        movieRepository.delete(movie);
    }

}
