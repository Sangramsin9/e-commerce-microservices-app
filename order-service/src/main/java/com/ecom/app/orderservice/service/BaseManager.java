package com.ecom.app.orderservice.service;

import com.ecom.app.orderservice.fiegnclient.ProductClient;
import com.ecom.app.orderservice.redis.CartRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseManager {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private CartRedisRepository cartRedisRepository;

    public ProductClient getProductClient() {
        return productClient;
    }

    public CartRedisRepository getCartRedisRepository() {
        return cartRedisRepository;
    }
}
