package com.example.cinemachainmanagement.repositories;

import com.example.cinemachainmanagement.entities.Customer;
import com.example.cinemachainmanagement.entities.TheaterRoom;
import com.example.cinemachainmanagement.entities.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketCrudRepository extends CrudRepository<Ticket, Long>{
    List<Ticket> findBySeatRoom(TheaterRoom room);
    List<Ticket> findAllByCustomer(Customer customer);
}
