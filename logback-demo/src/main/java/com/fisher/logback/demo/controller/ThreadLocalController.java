package com.fisher.logback.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/threadlocal")
public class ThreadLocalController {

    private ThreadLocal threadLocal = new ThreadLocal() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };


    @GetMapping("/test")
    public ResponseEntity  testThreadlocal() {
        SimpleDateFormat simpleDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd")).get();
        Integer integer = (Integer) this.threadLocal.get();
        return new ResponseEntity<>(simpleDateFormat.format(new Date()), HttpStatus.OK);
    }
}
