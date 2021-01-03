package com.fisher.statemachine.order;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 状态机的拦截器
 * @author shefenfei
 */
@Component
public class BizStateMachineChangeListener extends StateMachineInterceptorAdapter<OrderState, OrderEvent> {

    Logger logger = LoggerFactory.getLogger(BizStateMachineChangeListener.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void preStateChange(State<OrderState, OrderEvent> state,
                               Message<OrderEvent> message, Transition<OrderState, OrderEvent> transition,
                               StateMachine<OrderState, OrderEvent> stateMachine) {
        String orderNo = (String) message.getHeaders().getOrDefault("orderNo", "-1");
        String result = (String) redisTemplate.opsForValue().get("order:" + orderNo);
        Gson gson = new Gson();

        Order order = gson.fromJson(result, Order.class);
        order.setPaytime(new Date());
        order.setStates(state.getId());

        String s = gson.toJson(order);
        redisTemplate.opsForValue().set("order:" + orderNo, s);

        logger.info("state: {}, 1: {}, transition : {}, stateMachine: {}",state, message, transition, stateMachine);
    }
}
