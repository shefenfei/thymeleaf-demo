package com.fisher.arch.webflux.demo.service.impl;

import com.fisher.arch.webflux.demo.model.Apple;
import com.fisher.arch.webflux.demo.model.Fruit;
import com.fisher.arch.webflux.demo.service.FruitFactory;

public class AppleFactory implements FruitFactory {

    @Override
    public Fruit buildFruit() {
        Apple apple = new Apple();
        apple.setAddress("宁夏");
        apple.setPrice(8d);
        apple.setName("宁夏苹果");
        return apple;
    }
}
