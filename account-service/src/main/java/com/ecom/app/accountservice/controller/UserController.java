package com.ecom.app.accountservice.controller;

import com.ecom.app.accountservice.model.User;
import com.ecom.app.accountservice.model.UserRole;
import com.ecom.app.accountservice.service.UserManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserManager userManager;

    @GetMapping(value = "/users")
    public List<User> getAllUsers(){
        return userManager.getAllUsers();
    }

    @GetMapping( value = "/users", params = "username")
    public User getUserByName(@RequestParam("username") String username){
        User user = userManager.getUserByUsername(username);
        return user;
    }

    @GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable("id") Long id){
        return userManager.getUserById(id);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> addUser(@RequestBody User user, HttpServletRequest request) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(request.getRequestURI() + "/" + user.getId()));
        userManager.save(user);
        return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
    }

    @PostMapping(value = "/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody HashMap<String, Object> params, HttpServletRequest request) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        User user = userManager.getUserById(id);
        BeanUtils.copyProperties(params,  user, "username", "password", "id");
        headers.setLocation(new URI(request.getRequestURI() + "/" + user.getId()));
        userManager.save(user);
        return new ResponseEntity<User>(user, headers, HttpStatus.OK);
    }

}
