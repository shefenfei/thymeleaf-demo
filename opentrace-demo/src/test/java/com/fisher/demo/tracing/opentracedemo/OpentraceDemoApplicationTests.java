package com.fisher.demo.tracing.opentracedemo;

import com.fisher.demo.tracing.opentracedemo.dto.CarDto;
import com.fisher.demo.tracing.opentracedemo.entity.Car;
import com.fisher.demo.tracing.opentracedemo.mapstruct.mapper.CarMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpentraceDemoApplicationTests {

    @Test
    void contextLoads() {

    }

    @Autowired
    private CarMapper carMapper;

    @Test
    void  testMapper() {
        Car car = new Car();
        car.setColor("red");
        car.setMake("bmw");
        car.setNumberOfSeats(5);
        CarDto carDto = carMapper.carToCarDto(car);
        System.out.println(carDto.getManufacturer());
        System.out.println(carDto.getSeatCount());
    }

}
