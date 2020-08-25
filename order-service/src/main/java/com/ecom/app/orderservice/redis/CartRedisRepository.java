package com.ecom.app.orderservice.redis;

import java.util.Collection;

public interface CartRedisRepository {

    public void addItem(String key, Object item);
    public Collection<Object> getCart(String key, Class type);
    public void deleteItem(String key, Object item);
    public void deleteCart(String key);

}
