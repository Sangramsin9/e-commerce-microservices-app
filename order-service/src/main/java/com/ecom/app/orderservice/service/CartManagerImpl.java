package com.ecom.app.orderservice.service;

import com.ecom.app.orderservice.common.CartUtil;
import com.ecom.app.orderservice.model.Item;
import com.ecom.app.orderservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartManagerImpl extends BaseManager implements CartManager {

    @Override
    public void addItem(String cartId, Long productId, Integer quantity) {
        Product product = getProductClient().getProductById(productId);
        Item item = new Item(quantity,product, CartUtil.getSubTotalForItem(product,quantity));
        getCartRedisRepository().addItem(cartId, item);
    }

    @Override
    public List<Object> getCart(String cartId) {
        return (List<Object>) getCartRedisRepository().getCart(cartId, Item.class);
    }

    @Override
    public void updateQuantity(String cartId, Long productId, Integer quantity) {
        List<Item> cart = (List) getCartRedisRepository().getCart(cartId, Item.class);
        for(Item item : cart){
            if((item.getProduct().getId()).equals(productId)){
                getCartRedisRepository().deleteItem(cartId, item);
                item.setQuantity(quantity);
                item.setSubTotal(CartUtil.getSubTotalForItem(item.getProduct(),quantity));
                getCartRedisRepository().addItem(cartId, item);
            }
        }
    }

    @Override
    public void deleteItem(String cartId, Long productId) {
        List<Item> cart = (List) getCartRedisRepository().getCart(cartId, Item.class);
        for(Item item : cart){
            if((item.getProduct().getId()).equals(productId)){
                getCartRedisRepository().deleteItem(cartId, item);
            }
        }
    }

    @Override
    public boolean isExists(String cartId, Long productId) {
        List<Item> cart = (List) getCartRedisRepository().getCart(cartId, Item.class);
        for(Item item : cart){
            if((item.getProduct().getId()).equals(productId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Item> getAllItems(String cartId) {
        List<Item> items = (List)getCartRedisRepository().getCart(cartId, Item.class);
        return items;
    }

    @Override
    public void deleteCart(String cartId) {
        getCartRedisRepository().deleteCart(cartId);
    }
}
