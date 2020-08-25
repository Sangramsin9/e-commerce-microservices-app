package com.ecom.app.orderservice.controller;

import com.ecom.app.orderservice.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController extends BaseController {

    @GetMapping(value = "/cart")
    private ResponseEntity<List<Object>> getCart(@RequestHeader(value = "Cookie") String cartId){
        List<Object> cart = getOrderServiceappContext().getCartManager().getCart(cartId);
        return new ResponseEntity<List<Object>>(cart, HttpStatus.OK);
    }

    @PostMapping(value = "/cart", params = {"productId", "quantity"})
    private ResponseEntity addItemToCart(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity,
            @RequestHeader(value = "Cookie") String cartId) {
        List<Object> cart = getOrderServiceappContext().getCartManager().getCart(cartId);
        Product product = getOrderServiceappContext().getProductClient().getProductById(productId);

        if(cart.isEmpty()) {
            getOrderServiceappContext().getCartManager().addItem(cartId, productId, quantity);
        } else {
            if(getOrderServiceappContext().getCartManager().isExists(cartId, productId)){
                getOrderServiceappContext().getCartManager().updateQuantity(cartId, productId, quantity);
            }else {
                getOrderServiceappContext().getCartManager().addItem(cartId, productId, quantity);
            }
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/cart", params = "productId")
    private ResponseEntity removeItemFromCart(
            @RequestParam("productId") Long productId,
            @RequestHeader(value = "Cookie") String cartId){
        getOrderServiceappContext().getCartManager().deleteItem(cartId, productId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
