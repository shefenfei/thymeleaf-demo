package com.fisher.statemachine.order;

import com.fisher.statemachine.BizStateMachinePersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.state.State;

/**
 * 状态机配置
 *
 * @author shefenfei
 */
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

    Logger logger = LoggerFactory.getLogger(StateMachineConfig.class);

    @Bean
    public StateMachinePersister<OrderState, OrderEvent, String> machinePersist() {
        return new DefaultStateMachinePersister<>(new BizStateMachinePersist());
    }

    @Bean
    public StateMachineListener<OrderState, OrderEvent> stateMachineListener() {
        return new StateMachineListenerAdapter<OrderState, OrderEvent>() {
            @Override
            public void stateChanged(State<OrderState, OrderEvent> from, State<OrderState, OrderEvent> to) {
                logger.info("状态 from: {} to : {}", from, to);
            }
        };
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderState, OrderEvent> config) throws Exception {
        config.withConfiguration().listener(stateMachineListener());
//                .withPersistence().runtimePersister(machinePersist());
    }

    /**
     * 状态机的状态配置
     * 初始： order.created
     * 中间:  order.paid
     * 结束:  order.canceled 或 order.completed
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderState.CREATED)
                .state(OrderState.PAID)
                .end(OrderState.CANCELLED)
                .end(OrderState.COMPLETED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions.withExternal()
                //source(created) -->target(created)--> .event(submit) 指 从source中的状态通过event中的事件变更为target的状态
                .source(OrderState.CREATED).target(OrderState.CREATED).event(OrderEvent.SUBMIT)
                .and().withExternal()
                .source(OrderState.CREATED).target(OrderState.PAID).event(OrderEvent.PAYMENT)
                .and().withExternal()
                .source(OrderState.CREATED).target(OrderState.CANCELLED).event(OrderEvent.CANCEL_PAYMENT)
                .and().withExternal()
                .source(OrderState.PAID).target(OrderState.COMPLETED).event(OrderEvent.AUDUIT);
    }
}
