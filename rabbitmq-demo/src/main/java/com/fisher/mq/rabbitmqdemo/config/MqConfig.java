package com.fisher.mq.rabbitmqdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MqConfig {

    public static final String TEST_QUEUE = "test_queue";
    public static final String TEST_EXCHANGE = "test_exchange";
    public static final String TEST_ROUTING_KEY = "test_routing_key";

    @Bean
    public Queue queue() {
        return new Queue(TEST_QUEUE);
    }


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TEST_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TEST_ROUTING_KEY);
    }
}
