package com.fisher.arch.webflux.demo.model;

import lombok.Data;

@Data
public class Message {

    public static final Integer HELLO = 0;
    public static final Integer GOODBYE = 1;

    private String message;

    private Integer status;

    private Integer plazaId;
}
