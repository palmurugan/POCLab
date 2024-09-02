package com.pal.poc.spring.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ChannelConfiguration {

    @Bean
    public MessageChannel orderChannel() {
        return new DirectChannel();
    }
}
