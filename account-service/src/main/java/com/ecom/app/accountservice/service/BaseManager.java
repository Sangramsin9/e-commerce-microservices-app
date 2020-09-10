package com.ecom.app.accountservice.service;

import com.ecom.app.accountservice.repository.JwtTokenRepository;
import com.ecom.app.accountservice.repository.UserRepository;
import com.ecom.app.accountservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseManager {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected JwtTokenRepository jwtTokenRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public JwtTokenRepository getJwtTokenRepository() {
        return jwtTokenRepository;
    }
}
