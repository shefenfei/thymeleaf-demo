package com.fisher.arch.adminserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {


    @GetMapping("/get")
    public ResponseEntity<String> get() {



        return new ResponseEntity<>("{\n" +
                "  \"success\":true\n" +
                "}", HttpStatus.OK);
    }
}
