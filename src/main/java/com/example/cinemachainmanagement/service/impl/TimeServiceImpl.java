package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.entities.TheaterRoom;
import com.example.cinemachainmanagement.Mapper.Mapper;
import com.example.cinemachainmanagement.DTO.ShowtimeDTO;
import com.example.cinemachainmanagement.entities.Movie;
import com.example.cinemachainmanagement.entities.Showtime;
import com.example.cinemachainmanagement.entities.Theater;
import com.example.cinemachainmanagement.repositories.ShowtimeRepository;
import com.example.cinemachainmanagement.service.TimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TimeServiceImpl implements TimeService {
    private final Logger logger = LoggerFactory.getLogger(TimeServiceImpl.class);
    private final Mapper mapper;
    private final ShowtimeRepository ShowtimeRepo;

    public TimeServiceImpl(Mapper mapper, ShowtimeRepository showtimeRepo) {
        this.mapper = mapper;
        this.ShowtimeRepo = showtimeRepo;
    }

    @Override
    public List<Showtime> findByMovie(Movie movie) {
        return ShowtimeRepo.findByMovie(movie);
    }

    @Override
    public Optional<Showtime> findById(Long timeId) {
        return ShowtimeRepo.findById(timeId);
    }

    @Override
    public List<Showtime> filterBySelectedTime(List<Showtime> showTimes, String selectedMovieTime) {
        List<Showtime> filteredShowTimes = new ArrayList<>();
        showTimes.forEach(time -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(time.getDate());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based
            String monthString = month<10? "0"+month: String.valueOf(month);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String dayString = day<10? "0"+day: String.valueOf(day);
            String date = year+"-"+monthString+"-"+dayString;
            if(date.equals(selectedMovieTime)) {
                filteredShowTimes.add(time);
            }
        });
        if (filteredShowTimes.isEmpty()) {
            return new ArrayList<>();
        }
        return filteredShowTimes;
    }

    @Override
    public Boolean scheduleShowTime(Theater theater) {

        List<Movie> movies = theater.getMovies();
        List<TheaterRoom> rooms = theater.getRooms();
        if (movies == null || movies.isEmpty() || rooms == null || rooms.isEmpty()) {
            return false;
        }
        List<Showtime> showTimes = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        for(TheaterRoom room: rooms){
            if(checkShowtimesTodayForTheaterRoom(room))
                continue;
            deletePastShowtimes();
            LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0));
            while (540<=startTime.getHour()*60+startTime.getMinute() &&startTime.getHour()*60+startTime.getMinute() < 1260){
                for(Movie movie : movies){
                    LocalDateTime endTime = startTime.plusMinutes(movie.getDuration());
                    Showtime s = new Showtime(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()),
                            Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant()),
                            Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()),
                            room,
                            movie);
                    showTimes.add(s);
                    startTime =endTime.plusMinutes(10);
                    if (!startTime.toLocalDate().isEqual(LocalDate.now())) {
                        break; // Nếu đã qua ngày mới, dừng vòng lặp
                    }
                }
            }
        }
        try{
            showTimes = ShowtimeRepo.saveAll(showTimes);
            return !showTimes.isEmpty();
        }catch (Exception e){
            logger.warn(e.getMessage());
            return false;
        }
    }

    public boolean checkShowtimesTodayForTheaterRoom(TheaterRoom room) {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        Date startDate = java.sql.Timestamp.valueOf(startOfDay);
        Date endDate = java.sql.Timestamp.valueOf(endOfDay);
        List<Showtime> theaterShowtimesToday = ShowtimeRepo.findByTheaterRoomAndDateBetween(room, startDate, endDate);
        return !theaterShowtimesToday.isEmpty();
    }

    @Override
    public List<ShowtimeDTO> getShowTimeByTheater(Theater theater) {
        List<Showtime> showTimes = ShowtimeRepo.findByRoom_Theater(theater);
        return mapper.mapperEntityToDto(showTimes,ShowtimeDTO.class);
    }
    public void deletePastShowtimes() {
        List<Showtime> allShowtimes = ShowtimeRepo.findAll();

        // Filter showtimes that have ended before the current date
        List<Showtime> outdatedShowtimes = allShowtimes.stream()
                .filter(showtime -> {
                    LocalDateTime showtimeEndTime = showtime.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    return showtimeEndTime.toLocalDate().isBefore(LocalDate.now());
                })
                .collect(Collectors.toList());
        logger.info(String.valueOf(outdatedShowtimes.size()));
        // Delete outdated showtimes from the repository
        ShowtimeRepo.deleteAll(outdatedShowtimes);
    }
}
