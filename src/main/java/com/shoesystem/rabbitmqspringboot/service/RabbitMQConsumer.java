package com.shoesystem.rabbitmqspringboot.service;

import com.shoesystem.rabbitmqspringboot.config.RabbitMQConfig;
import com.shoesystem.rabbitmqspringboot.event.dto.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handle(OrderMessage orderMessage) {
        try {
            log.info("Received message by RabbitMQ: {}", orderMessage);
        } catch (Exception e) {
            log.error("Error processing order ❌ - {}", e.getMessage());
            throw new AmqpRejectAndDontRequeueException("Failed to process order", e);
        }
    }
}
