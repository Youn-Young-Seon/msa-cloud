package com.example.ordersService.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class Schema {
    private String type;
    private List<Field> fields;
    private boolean optional;
    private String name;
}
