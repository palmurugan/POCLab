package com.pal.poc.spring.integration.transformer;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.pal.poc.spring.integration.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Transformer;

import java.io.File;

public class OrderXMLTransformer {

    private static final Logger log = LoggerFactory.getLogger(OrderXMLTransformer.class);

    @Transformer
    public Order transformOrder(File file) {
        log.info("Received XML Order: {}", file.getAbsolutePath());
        return convertToOrder(file);
    }

    /**
     * Converts the XML file to Order object
     *
     * @param file the input file
     * @return the Order object
     */
    private Order convertToOrder(File file) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(file, Order.class);
        } catch (Exception e) {
            log.error("Error occurred while converting to Order: {}", e.getMessage());
        }
        return null;
    }
}
