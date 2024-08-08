package com.pal.poc.catalog.service.catalog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id", unique = true, nullable = false)
    private Long productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedDate;

    @Column(name = "status", nullable = false)
    private String status;

    public Product(Long productId, String name, String brandName, double price, String description, String createdBy, LocalDateTime createdDate, String status) {
        this.productId = productId;
        this.name = name;
        this.brandName = brandName;
        this.price = price;
        this.description = description;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.status = status;
    }

}
