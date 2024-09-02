package com.pal.poc.spring.integration.controller;

import com.pal.poc.spring.integration.model.Order;
import com.pal.poc.spring.integration.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Place an order
     *
     * @param order Order to be placed
     * @return Order
     */
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        log.info("Received order: {}", order);
        orderService.processOrder(order);
        return ResponseEntity.ok(order);
    }
}
