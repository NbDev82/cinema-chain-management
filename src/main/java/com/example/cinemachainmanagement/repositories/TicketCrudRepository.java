package com.example.cinemachainmanagement.repositories;

import com.example.cinemachainmanagement.entities.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketCrudRepository extends CrudRepository<Ticket, Long>{
}
