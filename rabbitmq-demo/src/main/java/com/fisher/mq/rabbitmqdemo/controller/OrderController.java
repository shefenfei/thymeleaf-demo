package com.fisher.mq.rabbitmqdemo.controller;

import com.fisher.mq.rabbitmqdemo.config.MqConfig;
import com.fisher.mq.rabbitmqdemo.model.Order;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
public class OrderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/test")
    public String test() {
        Order order = new Order();
        order.setMessage("hello world");
        order.setOrderNo("121212121");
        order.setPrice(12.0f);

        Gson gson = new Gson();
        String s = gson.toJson(order);
        rabbitTemplate.convertAndSend(MqConfig.TEST_EXCHANGE, MqConfig.TEST_ROUTING_KEY, s);
        return "success";
    }
}
