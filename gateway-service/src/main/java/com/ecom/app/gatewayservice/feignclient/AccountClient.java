package com.ecom.app.gatewayservice.feignclient;

import com.ecom.app.gatewayservice.model.User;
import com.ecom.app.gatewayservice.model.UserRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service")
public interface AccountClient {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public User getUser(@RequestParam("username") String username);

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user);

    @RequestMapping(value = "/users/role", method = RequestMethod.GET)
    public UserRole getRole(@RequestParam("username") String roleName);

}
