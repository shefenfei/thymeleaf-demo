package com.fisher.arch.web.controller;

import com.fisher.arch.service.IndexService;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/index")
public class IndexController {

    private IndexService indexService;

    public IndexController(IndexService service) {
        this.indexService = service;

    }

    @RequestMapping("/test")
    public Map<String, Object> index() {
        HashMap<String, Object> maps = Maps.newHashMap();
        maps.put("success", true);
        maps.put("data" , indexService.testIndex());
        maps.put("ddd", indexService.getUser());
        return maps;
    }


    @RequestMapping("/test1")
    public Map<String, Object> index1() {
        HashMap<String, Object> maps = Maps.newHashMap();
        maps.put("success", true);
        maps.put("data" , indexService.testIndex());
        maps.put("ddd", indexService.getUser());
        return maps;
    }
}
