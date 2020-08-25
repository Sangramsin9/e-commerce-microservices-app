package com.ecom.app.productinventory.service;

import com.ecom.app.productinventory.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManagerImpl extends BaseManager implements ProductManager {

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Product getOneById(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public List<Product> getAllProductsByName(String name) {
        return productRepository.findAllByProductName(name);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

}
