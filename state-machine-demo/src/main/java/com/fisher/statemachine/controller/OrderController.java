package com.fisher.statemachine.controller;

import com.fisher.statemachine.order.Order;
import com.fisher.statemachine.order.OrderEvent;
import com.fisher.statemachine.order.OrderState;
import com.fisher.statemachine.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;

    @Autowired
    private OrderService orderService;


    @PostMapping("/new")
    public ResponseEntity<String> saveOrder() {
        Order order = new Order();
        order.setStates(OrderState.CREATED);
        order.setOrderNo(UUID.randomUUID().toString());
        Order order1 = orderService.newOrder(order);
        return ResponseEntity.ok("");
    }


    @PostMapping("/pay")
    public ResponseEntity<String> payOrder(String orderNo) {
        orderService.payOrder(orderNo);
        return ResponseEntity.ok("");
    }


    @PostMapping("/cancel")
    public ResponseEntity<String> cancelOrder(String orderNo) {
        orderService.cancelOrder(orderNo);
        return ResponseEntity.ok("");
    }



    @GetMapping("/test")
    public String testOrder(OrderEvent event) {
        StateMachine<OrderState, OrderEvent> stateMachine = stateMachineFactory.getStateMachine("13323");
        stateMachine.start();
        stateMachine.sendEvent(event);

        Collection<State<OrderState, OrderEvent>> states = stateMachine.getState().getStates();
        logger.info("state: {}", event);

        orderService.payment("2020001212");
        return "";
    }
}
