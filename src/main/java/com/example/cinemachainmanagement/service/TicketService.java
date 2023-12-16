package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.TheaterRoomDTO;
import com.example.cinemachainmanagement.entities.*;
import jakarta.transaction.SystemException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    boolean persist(Ticket ticket);

    List<Ticket> createTicketsBySeatListId(String[] selectedSeatListId, Showtime time) throws SystemException;

    List<Ticket> findAllByCustomer(Customer customer);
    TheaterRoomDTO getOrderSeatsByRoomAndTime(TheaterRoom theaterRoom, Date startTime);
    Optional<Ticket> findTicketById(Long ticketId);
    List<Ticket> findTicketsByOrders(Orders orders);
}
