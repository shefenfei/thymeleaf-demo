package com.fisher.arch.webflux.demo.service;

import com.fisher.arch.webflux.demo.model.Apple;
import com.fisher.arch.webflux.demo.service.impl.AppleSellServiceImpl;

/**
 * 目标类做的事代理类也做，加强功能点
 */
public class AppleSellProxy implements AppleSellService {

    private AppleSellService appleSellService;

    public AppleSellProxy(AppleSellService appleSellService) {
        this.appleSellService = new AppleSellServiceImpl();
    }

    @Override
    public void sellApple(Apple apple) {
        System.out.println("先加点包装");
        appleSellService.sellApple(apple);
        System.out.println("再加点广告");
    }
}
