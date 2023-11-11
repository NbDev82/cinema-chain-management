package com.example.cinemachainmanagement.service.impl;


import com.example.cinemachainmanagement.controller.BookTicketController;
import com.example.cinemachainmanagement.entities.Showtime;
import com.example.cinemachainmanagement.entities.TheaterRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.cinemachainmanagement.entities.Theater;
import com.example.cinemachainmanagement.repositories.TheaterRepository;
import com.example.cinemachainmanagement.service.TheaterService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TheaterServiceImpl implements TheaterService {
    private final Logger logger = LoggerFactory.getLogger(BookTicketController.class);
    private final TheaterRepository theaterRepo;

    public TheaterServiceImpl(TheaterRepository theaterRepo) {
        this.theaterRepo = theaterRepo;
    }

    @Override
    public List<Theater> filterTheatersByLocation(List<Theater> theaters, String Location) { return theaters.stream().filter(theater -> theater.getLocation().equals(Location)).toList(); }

    @Override
    public List<Theater> filterTheatersByTheaterName(List<Theater> theaters, String selectedTheater) { return theaters.stream().filter(theater -> theater.getName().equals(selectedTheater)).toList(); }

    @Override
    public List<Theater> filterTheatersByRoom(List<Showtime> showtimes) {
        Set<Theater> theaterSet = new HashSet<>();
        TheaterRoom room = new TheaterRoom();
        room.setRoomId(0L);
        for(Showtime time : showtimes){
            Theater considerTheater =time.getRoom().getTheater();
            Theater ansTheater = new Theater(considerTheater);
            if(!room.getRoomId().equals(time.getRoom().getRoomId())){
                room = new TheaterRoom(time.getRoom());
            }
            room.getShowTimes().add(time);
            ansTheater.getRooms().add(room);
            theaterSet.add(ansTheater);
        }
        List<Theater> theaters = new ArrayList<>(theaterSet);
        if(theaters.isEmpty())
            theaters = new ArrayList<>();
        return theaters;
    }
}
