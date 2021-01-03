package com.fisher.arch.webflux.demo.service.impl;

import com.fisher.arch.webflux.demo.model.Apple;
import com.fisher.arch.webflux.demo.service.DemoService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            apple.setAddress("上海");
            apples.add(apple);
        }

        Gson gson = new Gson();

        redisTemplate.executePipelined((RedisCallback<String>) redisConnection -> {
            apples.forEach(apple -> {
                byte[] hkey = ("apple:" + apple.getNo()).getBytes();

                HashMap<byte[], byte[]> objectMap = new HashMap<>();
                objectMap.put("appleName".getBytes(), apple.getName().getBytes());
                objectMap.put("address".getBytes(), apple.getAddress().getBytes());
                objectMap.put("no".getBytes(), String.valueOf(apple.getNo()).getBytes());
//                objectMap.put("detail".getBytes(), gson.toJson(apple).getBytes());

                redisConnection.hMSet(hkey, objectMap);
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

    @Override
    public void getHash() {
//        getData();

        /*
        redisTemplate.execute((RedisCallback<List<Object>>) redisConnection -> {
            Object nativeConnection = redisConnection.getNativeConnection();
            Pipeline pipelined = ((Jedis) nativeConnection).pipelined();
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("username", "shefenfei");
            hashMap.put("password", "1234565");
            hashMap.put("email", "she1990111@sina.com");
            hashMap.put("address", "SHA");
            pipelined.hmset("apple:1", hashMap);
            return pipelined.syncAndReturnAll();
        });
         */
        get();
    }

    private void get() {
//        getData();

        redisTemplate.execute((RedisCallback<List<Object>>) redisConnection -> {
            Map<byte[], byte[]> map = redisConnection.hGetAll("user:2".getBytes());
            map.forEach((key, value) -> {
                String key1 = new String(key);
                String value1 = new String(value);
                LOGGER.info("{} : {}", key1, value1);
            });
            LOGGER.info("{}", map);
            return null;
        });


        Map<byte[], byte[]> map = redisTemplate.execute(new RedisCallback<Map<byte[], byte[]>>() {
            @Override
            public Map<byte[], byte[]> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.hGetAll("user:2".getBytes());
            }
        });

        LOGGER.info("{},", map);
        map.forEach((key, value) -> {
            String key1 = new String(key);
            String value1 = new String(value);
            LOGGER.info("{} : {}", key1, value1);
        });

        boolean b = redisTemplate.hasKey("user:2").booleanValue();
        LOGGER.info("b : {}", b);
    }

    private void getData() {
        Gson gson = new Gson();
        Apple apple = new Apple();
        apple.setName("appleName:");
        apple.setNo(1);
        apple.setAddress("上海");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", "shefenfei");
        hashMap.put("password", 123456);
        hashMap.put("email", "she1990111@sina.com");
        hashMap.put("address", "SHA");
        hashMap.put("detail", gson.toJson(apple));
        redisTemplate.opsForHash().putAll("user:2", hashMap);

        Map<Object, Object> entries = redisTemplate.opsForHash().entries("user:2");
        entries.forEach((key, value) -> {
            LOGGER.info("{} : {}", key, value);
        });
    }
}
