package com.fisher.arch.webflux.demo.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * 处理异步执行时出异常
 */
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        System.out.println("Exception message : " + throwable.getMessage());
        System.out.println("Method Name: " + method.getName());
        for (Object object : objects) {
            System.out.println("Paramster value --" + object);
        }
    }
}
