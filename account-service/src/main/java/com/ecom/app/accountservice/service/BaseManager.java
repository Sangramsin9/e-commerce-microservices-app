package com.ecom.app.accountservice.service;

import com.ecom.app.accountservice.repository.JwtTokenRepository;
import com.ecom.app.accountservice.repository.UserRepository;
import com.ecom.app.accountservice.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseManager {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserRoleRepository userRoleRepository;

    @Autowired
    protected JwtTokenRepository jwtTokenRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public UserRoleRepository getUserRoleRepository() {
        return userRoleRepository;
    }

    public JwtTokenRepository getJwtTokenRepository() {
        return jwtTokenRepository;
    }
}
