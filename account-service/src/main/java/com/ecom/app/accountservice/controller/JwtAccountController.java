package com.ecom.app.accountservice.controller;

import com.ecom.app.accountservice.model.JwtToken;
import com.ecom.app.accountservice.service.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class JwtAccountController {

    @Autowired
    JwtTokenManager jwtTokenManager;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<JwtToken> saveToken(@RequestBody JwtToken jwtToken) {
        JwtToken savedToken = jwtTokenManager.save(jwtToken);
        return new ResponseEntity<JwtToken>(savedToken, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> getToken(@PathVariable("token") String token) {
        Optional<JwtToken> tokenOpt = jwtTokenManager.findByToken(token);
        return new ResponseEntity<>( tokenOpt.isPresent(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/token", method = RequestMethod.DELETE)
    public void delete(@RequestBody JwtToken jwtToken) {
        jwtTokenManager.delete(jwtToken);
    }

}
