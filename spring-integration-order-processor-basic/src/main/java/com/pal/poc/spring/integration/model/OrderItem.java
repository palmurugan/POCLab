package com.pal.poc.spring.integration.model;

import java.math.BigDecimal;

public class OrderItem {

    private Long id;

    private Order order;

    private Long productId;

    private Integer quantity;

    private BigDecimal price;

}
