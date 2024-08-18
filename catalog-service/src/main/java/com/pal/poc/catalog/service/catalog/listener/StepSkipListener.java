package com.pal.poc.catalog.service.catalog.listener;

import com.pal.poc.catalog.service.catalog.domain.Product;
import com.pal.poc.catalog.service.catalog.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;

public class StepSkipListener implements SkipListener<ProductDTO, Product> {

    private static final Logger log = LoggerFactory.getLogger(StepSkipListener.class);

    /**
     * Log the error message when an item is skipped during read
     *
     * @param t Throwable
     */
    @Override
    public void onSkipInRead(Throwable t) {
        log.error("Error: While reading data: {}", t.getMessage());
    }

    /**
     * Log the error message when an item is skipped during write
     *
     * @param item Product
     * @param t    Throwable
     */
    @Override
    public void onSkipInWrite(Product item, Throwable t) {
        log.error("Error: While writing data: {}", t.getMessage());
    }

    /**
     * Log the error message when an item is skipped during process
     *
     * @param item ProductDTO
     * @param t    Throwable
     */
    @Override
    public void onSkipInProcess(ProductDTO item, Throwable t) {
        log.error("Error: While processing data:{} with the error {}", item.productId(), t.getMessage());
    }
}
