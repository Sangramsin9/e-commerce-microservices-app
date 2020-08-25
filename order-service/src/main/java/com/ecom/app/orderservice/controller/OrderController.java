package com.ecom.app.orderservice.controller;

import com.ecom.app.orderservice.common.OrderUtil;
import com.ecom.app.orderservice.exception.OrderException;
import com.ecom.app.orderservice.model.Item;
import com.ecom.app.orderservice.model.Order;
import com.ecom.app.orderservice.model.Product;
import com.ecom.app.orderservice.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrderController extends BaseController{

    @PostMapping(value = "/order/{userId}")
    private ResponseEntity saveOrder(@PathVariable("userId") Long userId, @RequestHeader(value = "Cookie") String cartId){
        Order order = new Order();
        List<Item> cart = getOrderServiceappContext().getCartManager().getAllItems(cartId);
        try {
            if (cart.isEmpty()) {
                throw new OrderException( "No item(s) in the cart." , HttpStatus.BAD_REQUEST);
            }
            User orderUser = new User();
            orderUser.setUsername(getOrderServiceappContext().getAccountClient().getUser(userId).getUsername());
            order.setItems(cart);
            order.setUser(orderUser);
            order.setTotal(OrderUtil.countTotalPrice(cart));
            order.setOrderedDate(LocalDate.now());
            order.setStatus("PAYMENT_PENDING");
            for (Item item : cart) {
                Long productId = item.getProduct().getId();
                Product product = getOrderServiceappContext().getProductClient().getProductById(productId);
                if(item.getQuantity() > product.getAvailability() || product.getAvailability() == 0) {
                    throw new OrderException("Item '"+ product.getProductName() +"' out of stock.",HttpStatus.BAD_REQUEST);
                }
                getOrderServiceappContext().getProductClient().updateProductQuantity(productId, product.getAvailability() - item.getQuantity());
            }
            getOrderServiceappContext().getOrderManager().saveOrder(order);
            getOrderServiceappContext().getCartManager().deleteCart(cartId);
        } catch (OrderException oe) {
            return new ResponseEntity( oe.getMessage() , oe.getHttpStatus());
        } catch (Exception ex){
            return new ResponseEntity( ex.getMessage() , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(order, HttpStatus.CREATED);
    }

    @GetMapping("/order")
    private ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = getOrderServiceappContext().getOrderManager().findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @GetMapping("/order/{id}")
    private ResponseEntity<Order> getOrder(@PathVariable("id") Long orderId) {
        Order orders = getOrderServiceappContext().getOrderManager().findOrderById(orderId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/order/user/{userId}")
    private ResponseEntity<List<Order>> getOrderByUserId(@PathVariable("userId") Long userId) {
        List<Order> orders = getOrderServiceappContext().getOrderManager().findOrderByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
