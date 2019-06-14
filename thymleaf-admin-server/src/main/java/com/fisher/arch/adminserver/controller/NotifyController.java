package com.fisher.arch.adminserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotifyController {


    @PostMapping("/email")
    public ResponseEntity<String> notifyEmail(@RequestBody String messgae) {
        System.out.println("error:-->" + messgae);
        System.out.println("太慢了");
        return new ResponseEntity<>("{\n" +
                "  \"message\" : \"服务太慢了啊\",\n" +
                "  \"code\": 4000\n" +
                "}", HttpStatus.OK);
    }
}
