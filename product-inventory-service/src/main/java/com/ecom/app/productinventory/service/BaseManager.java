package com.ecom.app.productinventory.service;

import com.ecom.app.productinventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseManager {

    @Autowired
    protected ProductRepository productRepository;

    public ProductRepository getProductRepository() {
        return productRepository;
    }
}
