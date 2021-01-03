package com.fisher.mq.rabbitmqdemo.model;

import lombok.Data;

@Data
public class Order {

    private String orderNo;
    private String message;
    private double price;
}
