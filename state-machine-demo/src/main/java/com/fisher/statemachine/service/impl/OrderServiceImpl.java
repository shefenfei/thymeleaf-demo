package com.fisher.statemachine.service.impl;

import com.fisher.statemachine.order.BizStateMachineChangeListener;
import com.fisher.statemachine.order.Order;
import com.fisher.statemachine.order.OrderEvent;
import com.fisher.statemachine.order.OrderState;
import com.fisher.statemachine.service.OrderService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;
    @Autowired
    private BizStateMachineChangeListener stateMachineChangeListener;


    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Order newOrder(Order order) {
        order.setStates(OrderState.CREATED);
        order.setCreateTime(new Date());
        Gson gson = new Gson();
        String json = gson.toJson(order);
        redisTemplate.opsForValue().set("order:"+order.getOrderNo(), json);
        StateMachine<OrderState, OrderEvent> stateMachine = build(order.getOrderNo());
        sendEvent(order.getOrderNo(), stateMachine, OrderEvent.SUBMIT);
        return order;
    }

    @Override
    public StateMachine<OrderState, OrderEvent> payment(String orderNo) {
        StateMachine<OrderState, OrderEvent> build = build(orderNo);
        sendEvent(orderNo, build, OrderEvent.PAYMENT);
        return build;
    }

    @Override
    public void payOrder(String orderNo) {
        //--spring.datasource.druid.driver-class-name=com.mysql.cj.jdbc.Driver
        StateMachine<OrderState, OrderEvent> stateMachine = build(orderNo);
        sendEvent(orderNo, stateMachine, OrderEvent.PAYMENT);
    }

    @Override
    public void cancelOrder(String orderNo) {
        StateMachine<OrderState, OrderEvent> stateMachine = build(orderNo);
        sendEvent(orderNo, stateMachine, OrderEvent.CANCEL_PAYMENT);
    }


    private void sendEvent(String orderNo, StateMachine<OrderState, OrderEvent> stateMachine, OrderEvent orderEvent) {
        Message<OrderEvent> message = MessageBuilder.withPayload(orderEvent)
                .setHeader("orderNo", orderNo)
                .build();
        stateMachine.sendEvent(message);
    }

    private StateMachine<OrderState, OrderEvent> build(String orderNo) {
        Gson gson = new Gson();
        String orderJson = (String) redisTemplate.opsForValue().get("order:" + orderNo);
        Order order = gson.fromJson(orderJson, Order.class);

        StateMachine<OrderState, OrderEvent> stateMachine = stateMachineFactory.getStateMachine(order.getOrderNo());
        OrderState orderState = OrderState.valueOf(order.getStates().name());
        stateMachine.stop();
        stateMachine.getStateMachineAccessor().doWithAllRegions(smf -> {
            smf.addStateMachineInterceptor(stateMachineChangeListener);
            smf.resetStateMachine(new DefaultStateMachineContext<>(orderState, null, null, null));
        });

        stateMachine.start();
        return stateMachine;
    }
}
