package com.ecom.app.orderservice.fiegnclient;

import com.ecom.app.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-inventory-service")
public interface ProductClient {

    @GetMapping(value = "/products/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId);

    @PostMapping(value = "/products/{id}/quantity")
    public Product updateProductQuantity(@PathVariable("id") Long id, @RequestParam("quantity") int quantity);

}
