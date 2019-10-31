package com.fisher.arch.webflux.demo.service.impl;

import com.fisher.arch.webflux.demo.model.Apple;
import com.fisher.arch.webflux.demo.service.AppleSellService;

public class AppleSellServiceImpl implements AppleSellService {

    @Override
    public void sellApple(Apple apple) {
        System.out.println("出售apple : " + apple.toString());
    }
}
