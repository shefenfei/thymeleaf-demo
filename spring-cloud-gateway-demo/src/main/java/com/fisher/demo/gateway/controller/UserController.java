package com.fisher.demo.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {


    @GetMapping("/{id}")
    public Mono<String> detail(@PathVariable("id") Long id) {
        return Mono.just("detail");
    }
}
