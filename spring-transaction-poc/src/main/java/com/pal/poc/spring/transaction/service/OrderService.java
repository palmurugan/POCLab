package com.pal.poc.spring.transaction.service;

import com.pal.poc.spring.transaction.entity.Order;
import com.pal.poc.spring.transaction.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        log.info("Saving order: {}", order);
        return orderRepository.save(order);
    }
}
