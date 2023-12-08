package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.DTO.TheaterRoomDTO;
import com.example.cinemachainmanagement.entities.Showtime;
import com.example.cinemachainmanagement.entities.TheaterRoom;
import com.example.cinemachainmanagement.entities.Ticket;
import jakarta.transaction.SystemException;

import java.util.List;

public interface TicketService {
    boolean persist(Ticket ticket);

    List<Ticket> createTicketsBySeatListId(String[] selectedSeatListId, Showtime time) throws SystemException;

    TheaterRoomDTO getOrderSeatsByRoom(TheaterRoom room);
}
