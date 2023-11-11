package com.example.cinemachainmanagement.mapper;

import com.example.cinemachainmanagement.DTO.CustomerDTO;
import com.example.cinemachainmanagement.DTO.TheaterDTO;
import com.example.cinemachainmanagement.entities.Theater;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
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

    public <S, D> D mapEntityToDto(S source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }
}
