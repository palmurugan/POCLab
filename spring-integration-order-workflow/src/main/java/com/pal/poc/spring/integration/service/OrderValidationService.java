package com.pal.poc.spring.integration.service;


import com.pal.poc.spring.integration.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Service;

@Service
public class OrderValidationService {

    private static final Logger log = LoggerFactory.getLogger(OrderValidationService.class);

    @Transformer
    public Order validateOrder(Order order) {
        log.info("Validating order: {}", order.getOrderId());
        order.setIsValid(order.getOrderId() != null);
        return order;
    }
}
