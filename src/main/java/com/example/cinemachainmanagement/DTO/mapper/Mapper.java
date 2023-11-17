package com.example.cinemachainmanagement.DTO.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    private final ModelMapper modelMapper;

    public Mapper() {
        this.modelMapper = new ModelMapper();
    }

    public <S, D> D convertToDto(S source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public <S, D> List<D> mapperEntityToDto(List<S> sourceList, Class<D> destinationType) {
        return sourceList.stream()
                .map(source -> modelMapper.map(source, destinationType))
                .collect(Collectors.toList());
    }

    public <S, D> D convertToEntity(S source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public <S, D> List<D> mapperDtoToEntity(List<S> sourceList, Class<D> destinationType) {
        return sourceList.stream()
                .map(source -> modelMapper.map(source, destinationType))
                .collect(Collectors.toList());
    }
}
