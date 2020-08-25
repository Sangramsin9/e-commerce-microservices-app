package com.ecom.app.orderservice.controller;

import com.ecom.app.orderservice.common.OrderServiceAppContext;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {

    @Autowired
    OrderServiceAppContext orderServiceappContext;

    public OrderServiceAppContext getOrderServiceappContext() {
        return orderServiceappContext;
    }

    public void setOrderServiceappContext(OrderServiceAppContext orderServiceappContext) {
        this.orderServiceappContext = orderServiceappContext;
    }
}
