package com.ecom.app.accountservice.service;

import com.ecom.app.accountservice.model.User;

import java.util.List;

public interface UserManager {

    public User getUserByUsername(String username);

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User save(User user);

}
