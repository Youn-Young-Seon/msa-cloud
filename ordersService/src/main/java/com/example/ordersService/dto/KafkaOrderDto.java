package com.example.ordersService.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KafkaOrderDto {
    private Schema schema;
    private Payload payload;
}
