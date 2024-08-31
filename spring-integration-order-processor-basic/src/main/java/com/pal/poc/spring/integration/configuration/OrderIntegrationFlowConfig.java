package com.pal.poc.spring.integration.configuration;

import com.pal.poc.spring.integration.transformer.OrderXMLTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;

//@Configuration
public class OrderIntegrationFlowConfig {

   // @Bean
    public IntegrationFlow orderXMLProcessingFlow(OrderXMLTransformer orderXMLTransformer) {
        return IntegrationFlow.from("fileInputChannel")
                .transform(orderXMLTransformer::transformOrder)
                .channel("processOrderChannel")
                .get();
    }
}
