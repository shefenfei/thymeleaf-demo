package com.fisher.arch.web.controller;

import com.fisher.arch.dao.repository.redis.StudentRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisopt")
public class RedisOperationController {

    @Autowired
    private StudentRedisRepository studentRedisRepository;


}
