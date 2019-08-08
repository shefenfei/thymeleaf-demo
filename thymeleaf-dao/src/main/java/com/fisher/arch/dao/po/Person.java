package com.fisher.arch.dao.po;

import lombok.Data;

import java.util.Date;

@Data
public class Person {

    private String id;
    private String username;
    private String address;
    private Date birthday;

}
