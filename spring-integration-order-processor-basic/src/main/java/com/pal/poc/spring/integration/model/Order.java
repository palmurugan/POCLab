package com.pal.poc.spring.integration.model;

import com.pal.poc.spring.integration.model.enumerations.OrderStatus;
import com.pal.poc.spring.integration.model.enumerations.ShippingMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Order {

    private Long id;

    private String orderId;

    private Long customerId;

    private LocalDate orderDate;

    private Set<OrderItem> items = new HashSet<>();

    private Address shippingAddress;

    private Address billingAddress;

    private LocalDate expirationDate;

    private String cvv;

    private Payment payment;

    private ShippingMethod shippingMethod;

    private BigDecimal shippingCost;

    private BigDecimal orderTotal;

    private OrderStatus orderStatus;
}
