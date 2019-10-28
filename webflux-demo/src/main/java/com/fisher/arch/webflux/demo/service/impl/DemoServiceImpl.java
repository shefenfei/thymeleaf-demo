package com.fisher.arch.webflux.demo.service.impl;

import com.fisher.arch.webflux.demo.service.DemoService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class DemoServiceImpl implements DemoService {

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
}
