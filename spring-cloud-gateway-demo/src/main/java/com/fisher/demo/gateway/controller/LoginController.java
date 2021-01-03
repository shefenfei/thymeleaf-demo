package com.fisher.demo.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/login")
@RestController
public class LoginController {

    @GetMapping("")
    public Mono<String> error() {
        return Mono.just("error 需要登录");
    }
}
