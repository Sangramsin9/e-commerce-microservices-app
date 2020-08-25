package com.ecom.app.accountservice.service;

import com.ecom.app.accountservice.model.JwtToken;

import java.util.Optional;

public interface JwtTokenManager {

    public JwtToken save(JwtToken jwtToken);

    public Optional<JwtToken> findByToken(String token);

    public void delete(JwtToken jwtToken);
}
