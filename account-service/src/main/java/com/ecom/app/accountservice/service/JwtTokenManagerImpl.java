package com.ecom.app.accountservice.service;

import com.ecom.app.accountservice.model.JwtToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtTokenManagerImpl extends BaseManager implements JwtTokenManager {

    @Override
    public JwtToken save(JwtToken jwtToken) {
        return getJwtTokenRepository().save(jwtToken);
    }

    @Override
    public Optional<JwtToken> findByToken(String token) {
        return getJwtTokenRepository().findById(token);
    }

    @Override
    public void delete(JwtToken jwtToken) {
        getJwtTokenRepository().delete(jwtToken);
    }
}
