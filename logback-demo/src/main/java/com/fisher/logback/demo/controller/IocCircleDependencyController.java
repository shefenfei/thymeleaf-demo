package com.fisher.logback.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ioc")
public class IocCircleDependencyController {

   private static final Logger LOGGER = LoggerFactory.getLogger(IocCircleDependencyController.class);

    @GetMapping("/iocTest")
    public ResponseEntity iocTest() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("debug日志 ： {}", "这是debug日志的开关");
        }
        LOGGER.info("默认的日志是 INFO ");
        LOGGER.warn("默认的日志是 WARN ");
        LOGGER.error("默认的日志是 ERROR ");
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
