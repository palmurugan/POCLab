package com.pal.poc.spring.integration.service;

import com.pal.poc.spring.integration.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderFulFillService {

    public String fulfillOrder(Order order) {
        return "Order Fulfilled for the orderId: " + order.getOrderId();
    }
}
