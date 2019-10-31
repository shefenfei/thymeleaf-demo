package com.fisher.arch.webflux.demo.service.impl;

import com.fisher.arch.webflux.demo.model.Banana;
import com.fisher.arch.webflux.demo.model.Fruit;
import com.fisher.arch.webflux.demo.service.FruitFactory;

public class BananaFactory implements FruitFactory {

    @Override
    public Fruit buildFruit() {
        Banana banana = new Banana();
        banana.setAddress("海南");
        banana.setColor("黄色");
        banana.setSize(10);
        banana.setName("香蕉");
        banana.setPrice(3.5D);
        return banana;
    }
}
