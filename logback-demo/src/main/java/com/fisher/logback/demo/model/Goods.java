package com.fisher.logback.demo.model;

import lombok.Data;

@Data
public class Goods {

    private String id;
    private String name;
    private Double price;
    private Double stock;
}
