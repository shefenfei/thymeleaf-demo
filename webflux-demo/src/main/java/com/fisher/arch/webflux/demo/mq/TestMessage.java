package com.fisher.arch.webflux.demo.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@EnableKafka
@Component
public class TestMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestMessage.class);

    @KafkaListener(topics = "test", groupId = "webflux-01")
    public void receiveMessage(String message) {
        LOGGER.info("收到 : {}", message);
    }
}
