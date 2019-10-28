package com.fisher.arch.web.controller;

import com.fisher.arch.service.IndexService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentry")
public class SentryController {

    private IndexService indexService;

    public SentryController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping("/test")
    public ResponseEntity sentryReport() {
        indexService.reportException();
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
