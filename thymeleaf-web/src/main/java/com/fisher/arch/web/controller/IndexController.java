package com.fisher.arch.web.controller;

import com.fisher.arch.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

    private IndexService indexService;

    public IndexController(IndexService service) {
        this.indexService = service;
    }

    @RequestMapping("/test")
    public Map<String, Object> index() {
        Map<String, Object> map = new HashMap<>();
        map.put("data" , indexService.testIndex());
        map.put("hello", indexService.getUser());
        log.error("data : {}" , map);
        log.info("data : {}" , map);
        return map;
    }

}
