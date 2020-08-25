package com.ecom.app.productinventory.service;

import com.ecom.app.productinventory.model.Product;

import java.util.List;

public interface ProductManager {

    public List<Product> getAllProduct();
    public List<Product> getAllProductByCategory(String category);
    public Product getOneById(Long id);
    public List<Product> getAllProductsByName(String name);
    public Product save(Product product);
    public void delete(Long productId);

}
