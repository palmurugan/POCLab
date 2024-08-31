package com.pal.poc.spring.integration.configuration;

import com.pal.poc.spring.integration.transformer.OrderXMLTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
@EnableIntegration
public class OrderIntegrationConfig {

    private static final Logger log = LoggerFactory.getLogger(OrderIntegrationConfig.class);

    @Autowired
    private OrderXMLTransformer orderXMLTransformer;

    @Bean
    public IntegrationFlow orderXMLProcessingFlow() {
        return IntegrationFlow
                .from(fileReadingMessageSource(),
                        pollingChannelAdapterSpec -> pollingChannelAdapterSpec.poller(
                                p -> p.fixedDelay(1000))
                )
                .transform(orderXMLTransformer::transformOrder)
                .handle(orderHandler())
                .get();
    }

    @Bean
    //@InboundChannelAdapter(value = "fileInputChannel", poller = @Poller(fixedDelay = "1000"))
    public FileReadingMessageSource fileReadingMessageSource() {
        // We are adding a filter to read only xml files
        CompositeFileListFilter<File> filter = new CompositeFileListFilter<>();
        filter.addFilter(new SimplePatternFileListFilter("*.xml"));

        FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
        fileReadingMessageSource.setDirectory(new File("/home/palmurugan/tmp/input"));
        fileReadingMessageSource.setFilter(filter);
        return fileReadingMessageSource;
    }

    @Bean
    public MessageHandler orderHandler() {
        return message -> {
            log.info("Received message: {}", message);
        };
    }

  /*  @Bean
    public FileWritingMessageHandler fileWritingMessageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("/home/palmurugan/tmp/output"));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setAutoCreateDirectory(true);
        handler.setExpectReply(false);
        return handler;
    }*/

    /*@Bean
    @ServiceActivator(inputChannel = "fileInputChannel")
    public MessageHandler fileWrittingMessageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("/home/palmurugan/tmp/output"));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setAutoCreateDirectory(true);
        handler.setExpectReply(false);
        return handler;
    }*/

    /*@Bean
    @ServiceActivator(inputChannel = "fileInputChannel", outputChannel = "processOrderChannel")
    public MessageHandler processOrderHandler() {
        return message -> {
            Order order = (Order) message.getPayload();

            // Process the transformed POJO (e.g. persist to a database)
            log.info("Processing order: {}", order);
        };
    }*/

}
