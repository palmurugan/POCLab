package com.pal.poc.spring.integration.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {

    private Long id;

    private Order order;

    private Long productId;

    private String productName;

    private Integer quantity;

    private BigDecimal price;

}
