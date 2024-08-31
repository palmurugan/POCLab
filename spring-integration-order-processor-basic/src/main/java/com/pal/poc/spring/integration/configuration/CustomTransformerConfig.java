package com.pal.poc.spring.integration.configuration;

import com.pal.poc.spring.integration.transformer.OrderXMLTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for custom transformer
 */
@Configuration
public class CustomTransformerConfig {

    @Bean
    public OrderXMLTransformer orderXMLTransformer() {
        return new OrderXMLTransformer();
    }
}
