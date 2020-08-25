package com.ecom.app.productinventory.controller;

import com.ecom.app.productinventory.model.Product;
import com.ecom.app.productinventory.service.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductManager productManager;

    @GetMapping(value = "/products")
    public List<Product> getAllProducts(){
        return productManager.getAllProduct();
    }

    @GetMapping(value = "/products", params = "category")
    public List<Product> getAllProductByCategory(@RequestParam("category") String category){
        return productManager.getAllProductByCategory(category);
    }

    @GetMapping (value = "/products/{id}")
    public Product getProductById(@PathVariable("id") long id){
        return productManager.getOneById(id);
    }

    @GetMapping (value = "/products", params = "name")
    public List<Product> getAllProductsByName(@RequestParam ("name") String name){
        return productManager.getAllProductsByName(name);
    }

    @PostMapping(value = "/products")
    private ResponseEntity<Product> addProduct(@RequestBody Product product, HttpServletRequest request) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(request.getRequestURI() + "/" + product.getId()));
        productManager.save(product);
        return new ResponseEntity<Product>(product, headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/products/{id}")
    private ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        productManager.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping(value = "/products/{id}/quantity")
    private Product updateProductQuantity(@PathVariable("id") Long id, @RequestParam("quantity") int quantity){
        Product product = productManager.getOneById(id);
        product.setAvailability(quantity);
        product = productManager.save(product);
        return product;
    }

}
