package com.example.catalogsService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogDTO {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
