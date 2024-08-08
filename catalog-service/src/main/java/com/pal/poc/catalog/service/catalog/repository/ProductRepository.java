package com.pal.poc.catalog.service.catalog.repository;

import com.pal.poc.catalog.service.catalog.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
