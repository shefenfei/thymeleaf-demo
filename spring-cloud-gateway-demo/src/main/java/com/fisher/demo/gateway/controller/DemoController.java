package com.fisher.demo.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/demo")
public class DemoController {


    @GetMapping("/save")
    public Mono<Object> save() {
        return Mono.just("hello world");
    }
}
