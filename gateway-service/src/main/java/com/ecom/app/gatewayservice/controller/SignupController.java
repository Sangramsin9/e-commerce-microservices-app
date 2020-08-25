package com.ecom.app.gatewayservice.controller;

import com.ecom.app.gatewayservice.feignclient.AccountClient;
import com.ecom.app.gatewayservice.model.User;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignupController {

    @Autowired
    AccountClient accountClient;

    @CrossOrigin("*")
    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<User> signUp(@RequestBody User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        ResponseEntity<User> userResponseEntity = accountClient.addUser(user);
        return userResponseEntity;
    }

}
