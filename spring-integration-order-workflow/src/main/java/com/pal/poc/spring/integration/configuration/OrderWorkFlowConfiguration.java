package com.pal.poc.spring.integration.configuration;

import com.pal.poc.spring.integration.model.Order;
import com.pal.poc.spring.integration.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageHandler;

/**
 * @author palmurugan
 */
@Configuration
public class OrderWorkFlowConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OrderWorkFlowConfiguration.class);

    private final OrderValidationService orderValidationService;

    private final OrderEnrichmentService orderEnrichmentService;

    private final PaymentService paymentService;

    private final NotificationService notificationService;

    private final OrderFulFillService orderFulFillService;

    public OrderWorkFlowConfiguration(OrderValidationService orderValidationService,
                                      OrderEnrichmentService orderEnrichmentService,
                                      PaymentService paymentService,
                                      NotificationService notificationService,
                                      OrderFulFillService orderFulFillService) {
        this.orderValidationService = orderValidationService;
        this.orderEnrichmentService = orderEnrichmentService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
        this.orderFulFillService = orderFulFillService;
    }

    /**
     * Order processing flow
     * 1. Order validation
     * 2. Enrich order
     * 3. Payment processing
     * 4. Notification
     * 5. Order fulfillment
     *
     * @return IntegrationFlow
     */
    @Bean
    public IntegrationFlow orderValidationFlow() {
        return IntegrationFlow.from("orderChannel")
                .transform(order -> orderValidationService.validateOrder((Order) order))
                .<Order, Boolean>route(Order::getIsValid, mapping -> mapping
                        .subFlowMapping(true, sf -> sf.channel("enrichOrderChannel"))
                        .subFlowMapping(false, sf -> sf.transform(order -> {
                            throw new RuntimeException("Invalid order");
                        }))
                )
                .get();
    }

    /**
     * Enrich order flow
     *
     * @return IntegrationFlow
     */
    @Bean
    public IntegrationFlow enrichOrderFlow() {
        return IntegrationFlow.from("enrichOrderChannel")
                .transform(order -> orderEnrichmentService.enrichOrder((Order) order))
                .channel("paymentChannel")
                .get();
    }

    /**
     * Payment processing flow
     *
     * @return IntegrationFlow
     */
    @Bean
    public IntegrationFlow paymentProcessingFlow() {
        return IntegrationFlow.from("paymentChannel")
                .transform(order -> paymentService.processPayment((Order) order))
                .publishSubscribeChannel(c -> c
                        .subscribe(s -> s.channel("notificationChannel"))
                        .subscribe(s -> s.channel("orderFulfillmentChannel"))
                )
                .get();
    }

    /**
     * Notification flow
     *
     * @return IntegrationFlow
     */
    @Bean
    public IntegrationFlow notificationFlow() {
        return IntegrationFlow.from("notificationChannel")
                .transform(order -> notificationService.sendNotification((Order) order))
                .handle(orderCompletionHandler())
                .get();
    }

    /**
     * Order fulfillment flow
     *
     * @return IntegrationFlow
     */
    @Bean
    public IntegrationFlow orderFulfillmentFlow() {
        return IntegrationFlow.from("orderFulfillmentChannel")
                .transform(order -> orderFulFillService.fulfillOrder((Order) order))
                .handle(orderCompletionHandler())
                .get();
    }

    /**
     * Order completion handler
     *
     * @return MessageHandler
     */
    @Bean
    public MessageHandler orderCompletionHandler() {
        return message -> {
            log.info("Order fulfilled: {}", message);
        };
    }
}
