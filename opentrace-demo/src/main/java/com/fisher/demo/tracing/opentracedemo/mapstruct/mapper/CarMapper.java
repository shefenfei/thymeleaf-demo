package com.fisher.demo.tracing.opentracedemo.mapstruct.mapper;

import com.fisher.demo.tracing.opentracedemo.dto.CarDto;
import com.fisher.demo.tracing.opentracedemo.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "make", target = "manufacturer")
    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car);
}
