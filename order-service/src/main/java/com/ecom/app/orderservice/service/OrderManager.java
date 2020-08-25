package com.ecom.app.orderservice.service;

import com.ecom.app.orderservice.model.Order;

import java.util.List;

public interface OrderManager {

    public Order saveOrder(Order order);

    public List<Order> findAll();

    public Order findOrderById(Long orderId);

    public List<Order> findOrderByUserId(Long userId);

}
