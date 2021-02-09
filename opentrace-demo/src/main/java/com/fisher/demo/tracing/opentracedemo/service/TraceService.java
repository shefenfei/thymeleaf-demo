package com.fisher.demo.tracing.opentracedemo.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TraceService {

    private static final Logger logger = LoggerFactory.getLogger(TraceService.class);

    private int count = 10;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Autowired
    private RedissonClient redisClient;
    @Autowired
    private RedisTemplate redisTemplate;

    public void doSomething() {
        redisTemplate.opsForValue().set("count", 10);
    }

    public void chargeNum() {
        logger.info("当前线程: {} 开始抢", Thread.currentThread().getName());
        RLock lock = redisClient.getLock("stock");
        try {
            lock.tryLock();
            int count = getCount();
            if (count > 0) {
                int c = count - 1;
                setCount(c);
                logger.info("抢中线程名: {} 抢中了1个 ，当前剩余: {}", Thread.currentThread().getName(), getCount());
            } else {
                logger.info("卖完了");
            }
        }  finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            } else {
                logger.info("线程名: {} 没抢中", Thread.currentThread().getName());
            }
        }
    }
}
