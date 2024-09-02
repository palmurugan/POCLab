package com.pal.poc.spring.integration.service;

import com.pal.poc.spring.integration.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    public Order processPayment(Order order) {
        log.info("Processing payment for the order {}", order.getOrderId());
        return order;// Assume the payment is accepted
    }
}
