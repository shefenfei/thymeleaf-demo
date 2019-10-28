package com.fisher.arch.webflux.demo.service;

import java.util.concurrent.Future;

public interface DemoService {

    String message();


    void printMessage();


    Future<String> asyncMethodWithReturnType();
}
