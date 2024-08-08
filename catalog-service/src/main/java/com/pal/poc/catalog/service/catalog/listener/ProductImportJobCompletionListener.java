package com.pal.poc.catalog.service.catalog.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class ProductImportJobCompletionListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(ProductImportJobCompletionListener.class);

    public void beforeJob() {
        log.info("Before Job");
    }

    public void afterJob() {
        log.info("After Job");
    }
}
