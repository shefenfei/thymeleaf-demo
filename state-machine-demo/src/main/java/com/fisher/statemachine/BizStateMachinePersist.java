package com.fisher.statemachine;

import com.fisher.statemachine.order.OrderEvent;
import com.fisher.statemachine.order.OrderState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;

public class BizStateMachinePersist implements StateMachinePersist<OrderState, OrderEvent, String> {
    HashMap<String, Object> hashMap =  new HashMap<String, Object>();

    Logger logger = LoggerFactory.getLogger(BizStateMachinePersist.class);

    @Override
    public void write(StateMachineContext<OrderState, OrderEvent> stateMachineContext, String result) throws Exception {
        String name = stateMachineContext.getState().name();
        logger.info("name: {}", name);
        logger.info("save: {}", result);
    }

    @Override
    public StateMachineContext<OrderState, OrderEvent> read(String s) throws Exception {
        logger.info("read: {}", s);
        Object o = hashMap.get(s);
        return ((StateMachineContext) o);
    }
}
