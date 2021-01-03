package com.fisher.springboot.autoconfig.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    Logger log = LoggerFactory.getLogger(MyService.class);

    public String sayHello() {

        log.info("hi");
        return "hello";
    }
}
