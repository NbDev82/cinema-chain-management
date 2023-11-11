package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.controller.BookTicketController;
import com.example.cinemachainmanagement.entities.Movie;
import com.example.cinemachainmanagement.entities.Showtime;
import com.example.cinemachainmanagement.entities.Theater;
import com.example.cinemachainmanagement.repositories.ShowtimeRepository;
import com.example.cinemachainmanagement.service.TimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class TimeServiceImpl implements TimeService {
    private final Logger logger = LoggerFactory.getLogger(BookTicketController.class);

    private final ShowtimeRepository ShowtimeRepo;

    public TimeServiceImpl(ShowtimeRepository showtimeRepo) {
        this.ShowtimeRepo = showtimeRepo;
    }

    @Override
    public List<Showtime> findByMovie(Movie movie) {
        return ShowtimeRepo.findByMovie(movie);
    }

    @Override
    public List<Showtime> filterBySelectedTime(List<Showtime> showTimes, String selectedMovieTime) {
        List<Showtime> filteredShowTimes = new ArrayList<>();
        showTimes.forEach(time -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(time.getDate());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String date = year+"-"+month+"-"+day;
            if(date.equals(selectedMovieTime)) {
                filteredShowTimes.add(time);
            }
        });
        if (filteredShowTimes.isEmpty()) {
            return new ArrayList<>();
        }
        return filteredShowTimes;
    }
}
