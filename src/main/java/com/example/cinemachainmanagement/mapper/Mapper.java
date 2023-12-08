package com.example.cinemachainmanagement.Mapper;

import com.example.cinemachainmanagement.DTO.ShowtimeDTO;
import com.example.cinemachainmanagement.DTO.TicketDTO;
import com.example.cinemachainmanagement.entities.Showtime;
import com.example.cinemachainmanagement.entities.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    private final ModelMapper mapper;

    public Mapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public <S, D> List<D> mapperEntityToDto(List<S> sourceList, Class<D> destinationType) {
        return sourceList.stream()
                .map(source -> mapper.map(source, destinationType))
                .collect(Collectors.toList());
    }

    public <S, D> D mapperEntityToDto(S source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }

    public TicketDTO mapEntityToCustomDto(Ticket ticket) {
        TicketDTO ticketDTO = mapperEntityToDto(ticket,TicketDTO.class);
        Showtime time = ticket.getShowTime();

        ticketDTO.setDuration(time.getMovie().getDuration());
        ticketDTO.setGenre(time.getMovie().getGenre());
        ticketDTO.setRoomNumber(time.getRoom().getRoomNumber());
        ticketDTO.setTheaterName(time.getRoom().getTheater().getName());
        ticketDTO.setTitle(time.getMovie().getTitle());
        ticketDTO.setStartTime(time.getStartTime());
        ticketDTO.setDate(time.getDate());
        return ticketDTO;
    }
    public List<ShowtimeDTO> mapEntityToCustomDto(List<Showtime> showtimes){
        return mapperEntityToDto(showtimes,ShowtimeDTO.class);
    }
}
