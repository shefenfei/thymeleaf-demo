package com.fisher.statemachine.service;

import com.fisher.statemachine.order.Order;
import com.fisher.statemachine.order.OrderEvent;
import com.fisher.statemachine.order.OrderState;
import org.springframework.statemachine.StateMachine;

public interface OrderService {

    Order newOrder(Order order);

    StateMachine<OrderState, OrderEvent> payment(String orderNo);

    void payOrder(String orderNo);

    void cancelOrder(String orderNo);
}
