package com.ecom.app.orderservice.common;

import com.ecom.app.orderservice.model.Product;

import java.math.BigDecimal;

public class CartUtil {

    public static BigDecimal getSubTotalForItem(Product product, int quantity){
        return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }

}
