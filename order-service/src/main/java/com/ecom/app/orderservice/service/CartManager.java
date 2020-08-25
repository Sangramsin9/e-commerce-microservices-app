package com.ecom.app.orderservice.service;

import com.ecom.app.orderservice.model.Item;

import java.util.List;

public interface CartManager {

    public void addItem(String cartId, Long productId, Integer quantity);
    public List<Object> getCart(String cartId);
    public void updateQuantity(String cartId, Long productId, Integer quantity);
    public void deleteItem(String cartId, Long productId);
    public boolean isExists(String cartId, Long productId);
    public List<Item> getAllItems(String cartId);
    public void deleteCart(String cartId);

}
