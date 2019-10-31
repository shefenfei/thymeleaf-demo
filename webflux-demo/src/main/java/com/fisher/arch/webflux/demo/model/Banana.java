package com.fisher.arch.webflux.demo.model;

import lombok.Data;

@Data
public class Banana extends Fruit {
    private String address;
    private String color;
    private Integer size;

}
