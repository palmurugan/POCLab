package com.pal.poc.spring.integration.model;

import com.pal.poc.spring.integration.model.enumerations.OrderStatus;
import com.pal.poc.spring.integration.model.enumerations.ShippingMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class Order {

    private Long id;

    private String orderId;

    private String customerId;

    private String orderDate;

    private Set<OrderItem> items = new HashSet<>();

    private Address shippingAddress;

    private Address billingAddress;

    private String expirationDate;

    private String cvv;

    private Payment payment;

    private ShippingMethod shippingMethod;

    private BigDecimal shippingCost;

    private BigDecimal orderTotal;

    private OrderStatus orderStatus;

    private Boolean isValid;
    
    private String trackId;
}
