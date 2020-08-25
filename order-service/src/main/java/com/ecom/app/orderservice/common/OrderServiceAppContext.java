package com.ecom.app.orderservice.common;

import com.ecom.app.orderservice.fiegnclient.AccountClient;
import com.ecom.app.orderservice.fiegnclient.ProductClient;
import com.ecom.app.orderservice.service.CartManager;
import com.ecom.app.orderservice.service.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceAppContext {

    @Autowired
    OrderManager orderManager;

    @Autowired
    CartManager cartManager;

    @Autowired
    AccountClient accountClient;

    @Autowired
    ProductClient productClient;

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public CartManager getCartManager() {
        return cartManager;
    }

    public void setCartManager(CartManager cartManager) {
        this.cartManager = cartManager;
    }

    public AccountClient getAccountClient() {
        return accountClient;
    }

    public void setAccountClient(AccountClient accountClient) {
        this.accountClient = accountClient;
    }

    public ProductClient getProductClient() {
        return productClient;
    }

    public void setProductClient(ProductClient productClient) {
        this.productClient = productClient;
    }
}
