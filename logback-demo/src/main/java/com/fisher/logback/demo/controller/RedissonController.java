package com.fisher.logback.demo.controller;

import com.fisher.logback.demo.model.Goods;
import com.fisher.logback.demo.model.Person;
import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisson")
public class RedissonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedissonController.class);

    @Resource
    private RedissonClient redissonClient;

    @PostMapping(value = "/save")
    public ResponseEntity save() {
        RAtomicLong myLong = redissonClient.getAtomicLong("myLong");
        boolean b = myLong.compareAndSet(2, 19);
        myLong.compareAndSetAsync(1, 200).whenComplete((result, e) -> {
            System.out.println(result);
            System.out.println(e);
        });
        RKeys keys = redissonClient.getKeys();
        long count = keys.count();

        RBucket<Person> personRBucket = redissonClient.getBucket("person");
        Person person = new Person();
        person.setId("1");
        person.setName("shefenfei");
        person.setSex("male");
        person.setBirthday(new Date());
        personRBucket.set(person);

        personRBucket.getAsync().whenComplete((result, ex) -> {
            LOGGER.info("init data : {}", result);
        });

        Person andSet = personRBucket.getAndSet(new Person("2", "new user", "male", new Date()));
        LOGGER.info("getAndSet : {}", andSet);

        boolean isSet = personRBucket.compareAndSet(person, new Person("3", "username3", "female", new Date()));
        LOGGER.info("compareAndSet : {}", isSet);

        RBuckets buckets = redissonClient.getBuckets();
        HashMap<String, Person> stringPersonHashMap = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            Person person1 = new Person("" + i, "username" + i, i % 2 == 0 ? "male" : "female", new Date());
            stringPersonHashMap.put("person" + i, person1);
        }
        buckets.set(stringPersonHashMap);

        buckets.getAsync("person1", "person2").whenComplete((result, ex) -> {
            LOGGER.info("data : {}", result);
        });

        RGeo<String> geo = redissonClient.getGeo("test");
        geo.add(new GeoEntry(13.361389, 38.115556, "Palermo"),
                new GeoEntry(15.087269, 37.502669, "Catania"));
        geo.addAsync(37.618423, 55.751244, "Moscow");

        Double moscow = geo.getScore("Moscow");
        LOGGER.info("geo: {}", moscow);

        RLock orderLock = redissonClient.getLock("order");
        orderLock.lock(20, TimeUnit.SECONDS);
        try {
            //处理业务逻辑
        } finally {
            orderLock.unlock();
        }
        return new ResponseEntity<>(b + "" + count, HttpStatus.OK);
    }


    @PostMapping("/addStock")
    public ResponseEntity addStock(double stock) {
        Goods goods = new Goods();
        goods.setId("ISBN:201908071290122");
        goods.setName("特价mac电脑");
        goods.setPrice(2999D);
        goods.setStock(10D);

        RBucket<Object> bucket = redissonClient.getBucket(goods.getId());
        bucket.set(goods);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/panicBuying")
    public ResponseEntity panicBuying() {
        //模拟多个用户同时抢购这10件商品，并使用redis做lock操作
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
        RBucket<Object> bucket = redissonClient.getBucket("ISBN:201908071290122");

        Goods goods = (Goods) bucket.get();
        RLock goodsLock = redissonClient.getLock("goodsLock");
        goodsLock.lock(10, TimeUnit.SECONDS);
        try {
            //抢光了
            if (goods.getStock() == 0) {
                LOGGER.warn("商品: " + goods.getName() + "库存为: {}, 抢光了！线程 : {} 没有抢到 ！", goods.getStock(), Thread.currentThread().getName());
            } else {
                double currentStock = goods.getStock();
                goods.setStock(currentStock - 1);
                bucket.set(goods);
                LOGGER.info("线程: {} 抢到了电脑 ！", Thread.currentThread().getName());
            }
        } finally {
            goodsLock.unlock();
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }


    //模拟多用户同时抢购同一件商品，在将来的生产中，将热数据通过其他的方法加载到redis中
    @GetMapping("/testMulti")
    public ResponseEntity testMultiUser() {
        RBucket<Object> bucket = redissonClient.getBucket("ISBN:201908071290122");
        Goods goods = (Goods) bucket.get();

        for (int i=0; i< 1000; i++) {
            Thread thread = new Thread(() -> {
                RLock goodsLock = redissonClient.getLock("goodsLock");
                goodsLock.lock(10, TimeUnit.SECONDS);

                try {
                    //抢光了
                    if (goods.getStock() == 0) {
                        LOGGER.warn("商品: " + goods.getName() + "库存为: {}, 抢光了！线程 : {} 没有抢到 ！", goods.getStock(), Thread.currentThread().getName());
                    } else {
                        double currentStock = goods.getStock();
                        goods.setStock(currentStock - 1);
                        bucket.set(goods);
                        LOGGER.info("用户: {} 抢到了电脑 ！", Thread.currentThread().getName());
                    }
                } finally {
                    goodsLock.unlock();
                }
            }, "[用户编号 : " + i + " ]");
            thread.start();
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
