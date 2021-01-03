package com.fisher.cache.springbootcacheredis.controller;

import com.fisher.cache.springbootcacheredis.bean.User;
import com.fisher.cache.springbootcacheredis.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        List<User> allUser = cacheService.findAllUser(1, true);
        User user = cacheService.findById(1);
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }
}
