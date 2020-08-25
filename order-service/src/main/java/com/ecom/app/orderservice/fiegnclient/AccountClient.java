package com.ecom.app.orderservice.fiegnclient;

import com.ecom.app.orderservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service")
public interface AccountClient {

    @GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable("id") Long id);

}
