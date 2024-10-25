package com.shoesystem.rabbitmqspringboot.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderMessage {
    private String eventId;
    private String message;
}
