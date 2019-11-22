package com.fisher.arch.webflux.demo.service.impl;

import com.fisher.arch.webflux.demo.model.Apple;
import com.fisher.arch.webflux.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Service
public class DemoServiceImpl implements DemoService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoService.class);

    @Override
    public String message() {
        try {
            Thread.sleep(1000L);
            System.out.println(Thread.currentThread().getName() + "hello from server");
            printMessage();
            return "hello from server";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public void printMessage() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(1 / 0);
                System.out.println(Thread.currentThread().getName() + "hello from other thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public Future<String> asyncMethodWithReturnType() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
            return new AsyncResult<String>("hello world from async return method !");
        } catch (InterruptedException e) {
            //
        }
        return null;
    }

    @Override
    public void batchInsert() {
        ArrayList<Apple> apples = new ArrayList<>();
        for (int i=0;  i< 100000; i ++) {
            Apple apple = new Apple();
            apple.setName("appleName:" +i);
            apple.setNo(i);
            apples.add(apple);
        }


        redisTemplate.executePipelined((RedisCallback<String>) redisConnection -> {
            apples.forEach(apple -> {
                byte[] hkey = ("apple:" + apple.getNo() + "").getBytes();
                byte[] objKey = "name".getBytes();
                byte[] objValue = apple.getName().getBytes();
                redisConnection.hSet(hkey, objKey, objValue);
            });
            return null;
        });
    }

    @Override
    public void saveHash() {
        ArrayList<Map<byte[], byte[]>> maps = new ArrayList<>();
        for (int i=0; i< 100000; i ++) {
            HashMap<byte[], byte[]> hashMap = new HashMap<>();
            hashMap.put("username".getBytes(), ("username" + i).getBytes());
            hashMap.put("password".getBytes(), String.valueOf(123456 + i).getBytes());
            maps.add(hashMap);
        }

        redisTemplate.executePipelined((RedisCallback<String>) redisConnection -> {
            for (int i =0; i< maps.size(); i ++ ) {
                redisConnection.hMSet(("user:" + i).getBytes(), maps.get(i));
            }
            return null;
        });
        LOGGER.info("完成");
    }
}
