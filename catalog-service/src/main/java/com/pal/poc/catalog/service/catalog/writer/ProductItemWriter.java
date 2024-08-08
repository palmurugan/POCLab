package com.pal.poc.catalog.service.catalog.writer;

import com.pal.poc.catalog.service.catalog.domain.Product;
import com.pal.poc.catalog.service.catalog.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductItemWriter implements ItemWriter<Product> {

    private static final Logger log = LoggerFactory.getLogger(ProductItemWriter.class);

    @Autowired
    private ProductRepository productRepository;

    /**
     * Write the provided Product items to the database
     *
     * @param items Chunk<? extends Product>
     * @throws Exception
     */
    @Override
    public void write(Chunk<? extends Product> items) throws Exception {
        log.info("Writing data for Product: {}", items.getItems().size());
        productRepository.saveAll(items.getItems());
    }
}
