package com.pal.poc.spring.integration.service;

import com.pal.poc.spring.integration.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderEnrichmentService {

    private static final Logger log = LoggerFactory.getLogger(OrderEnrichmentService.class);

    public Order enrichOrder(Order order) {
        log.info("Enriching order: {}", order.getOrderId());
        order.setTrackId("TRK" + order.getOrderId());
        return order;
    }
}
