package com.pal.poc.catalog.service.catalog.processor;

import com.pal.poc.catalog.service.catalog.domain.Product;
import com.pal.poc.catalog.service.catalog.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Objects;

public class ProductItemProcessor implements ItemProcessor<ProductDTO, Product> {

    private static final Logger log = LoggerFactory.getLogger(ProductItemProcessor.class);

    private static final String ACTIVE_STATUS = "ACTIVE";

    private static final String ADMIN_USER = "admin";

    /**
     * Process the provided ProductDTO and convert it to Product
     *
     * @param productDTO ProductDTO
     * @return Product
     * @throws Exception
     */
    @Override
    public Product process(ProductDTO productDTO) throws Exception {
        if (Objects.nonNull(productDTO)) {
            log.debug("Processing data for ProductDTO: {}", productDTO);
            return new Product(productDTO.productId(), productDTO.productName(), productDTO.productBrand(),
                    productDTO.price(), productDTO.description(),
                    ADMIN_USER, java.time.LocalDateTime.now(), ACTIVE_STATUS);
        } else {
            log.error("Error: While processing data: ProductDTO is null");
            return null;
        }
    }
}
