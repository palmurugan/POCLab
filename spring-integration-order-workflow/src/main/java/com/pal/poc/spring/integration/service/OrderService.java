package com.pal.poc.spring.integration.service;

import com.pal.poc.spring.integration.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final MessageChannel orderChannel;

    public OrderService(MessageChannel orderChannel) {
        this.orderChannel = orderChannel;
    }

    /**
     * Process the order
     *
     * @param order Order to process
     */
    public void processOrder(Order order) {
        log.info("Processing order: {}", order.getOrderId());
        orderChannel.send(MessageBuilder.withPayload(order).build());
    }
}