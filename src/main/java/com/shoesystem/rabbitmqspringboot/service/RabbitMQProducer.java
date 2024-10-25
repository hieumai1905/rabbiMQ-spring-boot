package com.shoesystem.rabbitmqspringboot.service;

import com.shoesystem.rabbitmqspringboot.config.RabbitMQConfig;
import com.shoesystem.rabbitmqspringboot.event.dto.OrderMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(OrderMessage orderMessage) {
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,
                    RabbitMQConfig.ROUTING_KEY,
                    orderMessage
            );
            log.info("Message sent: {}", orderMessage);
        } catch (Exception e) {
            log.error("Error while sending order to RabbitMQ ❌ - {}", e.getMessage());
            throw new RuntimeException("Error sending order to queue", e);
        }
    }
}
