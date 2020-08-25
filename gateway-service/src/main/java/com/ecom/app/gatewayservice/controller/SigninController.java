package com.ecom.app.gatewayservice.controller;

import com.ecom.app.gatewayservice.model.SigninRequest;
import com.ecom.app.gatewayservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SigninController {

    @Autowired
    AuthService authService;

    @CrossOrigin("*")
    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity<String> signin(@RequestBody SigninRequest loginRequest) {
        String token = authService.signin(loginRequest.getUsername(),loginRequest.getPassword());
        HttpHeaders headers = new HttpHeaders();
        List<String> headerlist = new ArrayList<>();
        headerlist.add("Content-Type");
        headerlist.add(" Accept");
        headerlist.add("X-Requested-With");
        headerlist.add("Authorization");
        headers.setAccessControlAllowHeaders(headerlist);
        List<String> exposeList = new ArrayList<>();
        exposeList.add("Authorization");
        headers.setAccessControlExposeHeaders(exposeList);
        headers.set("Authorization", token);
        return new ResponseEntity<String>(token, headers, HttpStatus.CREATED);
    }

    @CrossOrigin("*")
    @PostMapping("/signout")
    @ResponseBody
    public ResponseEntity<String> logout (@RequestHeader(value="Authorization") String token) {
        HttpHeaders headers = new HttpHeaders();
        if (authService.signout(token)) {
            headers.remove("Authorization");
            return new ResponseEntity<String>("logged out", headers, HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Logout Failed", headers, HttpStatus.NOT_MODIFIED);
    }

}
