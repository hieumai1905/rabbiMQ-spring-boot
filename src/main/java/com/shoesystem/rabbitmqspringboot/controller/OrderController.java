package com.shoesystem.rabbitmqspringboot.controller;

import com.shoesystem.rabbitmqspringboot.event.dto.OrderMessage;
import com.shoesystem.rabbitmqspringboot.service.RabbitMQProducer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/rabbitmq")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {

    RabbitMQProducer rabbitMQProducer;

    @PostMapping("/send")
    public void sendMessage(@RequestBody String message) {
        rabbitMQProducer.sendMessage(OrderMessage.builder()
                .eventId(UUID.randomUUID().toString())
                .message(message)
                .build());
    }
}
