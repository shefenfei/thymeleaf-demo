package com.fisher.arch.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/other")
public class OtherController {

    @GetMapping("/test")
    public ResponseEntity foo() {
        return new ResponseEntity<>("{\n" +
                "  \"success\":true,\n" +
                "  \"code\":200\n" +
                "}", HttpStatus.OK);
    }
}
