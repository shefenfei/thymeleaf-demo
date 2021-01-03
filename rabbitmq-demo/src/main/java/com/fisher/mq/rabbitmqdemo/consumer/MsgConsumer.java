package com.fisher.mq.rabbitmqdemo.consumer;

import com.fisher.mq.rabbitmqdemo.config.MqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MsgConsumer {

    Logger logger  = LoggerFactory.getLogger(MsgConsumer.class);

    @RabbitListener(queues = MqConfig.TEST_QUEUE)
    public void onMessage(String message) {
        logger.info(message);
    }
}
