package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.entities.Seat;
import com.example.cinemachainmanagement.entities.Showtime;
import com.example.cinemachainmanagement.entities.Ticket;
import com.example.cinemachainmanagement.repositories.TicketCrudRepository;
import com.example.cinemachainmanagement.service.TheaterService;
import com.example.cinemachainmanagement.service.TicketService;
import jakarta.transaction.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TicketServiceImpl implements TicketService {
    private final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);
    private final Lock lock = new ReentrantLock();;
    private final TicketCrudRepository crudTicketRepo;
    private final TheaterService theaterService;


    public TicketServiceImpl(TicketCrudRepository crudTicketRepo, TheaterService theaterService) {
        this.crudTicketRepo = crudTicketRepo;
        this.theaterService = theaterService;
//        this.lock = lock;
    }

    @Override
    public boolean persist(Ticket ticket) {
        try{
            crudTicketRepo.save(ticket);
            return true;
        }catch (IllegalArgumentException | OptimisticLockingFailureException e){
            logger.info(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public List<Ticket> createTicketsBySeatListId(String[] selectedSeatListId, Showtime time) throws SystemException {
        List<Ticket> tickets = new ArrayList<>();
        try{
            lock.lock();

            for (String seatId : selectedSeatListId) {
                logger.info(seatId);
                Optional<Seat> seatOptional = theaterService.getSeatById(Long.valueOf(seatId));
                if (seatOptional.isPresent() && !seatOptional.get().isReserved()) {
                    Seat seat = seatOptional.orElse(null);
                    Ticket ticket = Ticket.builder()
                            .ticketStatus(false)
                            .showTime(time)
                            .seat(seat)
                            .price(45000)
                            .build();
                    tickets.add(ticket);
                    if (!persist(ticket)) {
                        break;
                    }
                    seat.setReserved(true);
                } else{
                    tickets.clear();
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
        return tickets;
    }
}
