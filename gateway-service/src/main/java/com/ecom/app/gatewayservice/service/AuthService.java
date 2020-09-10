package com.ecom.app.gatewayservice.service;

import com.ecom.app.gatewayservice.auth.JwtAuthException;
import com.ecom.app.gatewayservice.auth.JwtToken;
import com.ecom.app.gatewayservice.auth.JwtTokenProvider;
import com.ecom.app.gatewayservice.feignclient.AccountClient;
import com.ecom.app.gatewayservice.feignclient.JwtTokenClient;
import com.ecom.app.gatewayservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountClient accountClient;
    @Autowired
    private JwtTokenClient jwtTokenClient;

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    password));
            User user = accountClient.getUser(username);
            if (user == null || user.getRoles().isEmpty()) {
                throw new JwtAuthException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
            }
            //NOTE: normally we dont need to add "ROLE_" prefix. Spring does automatically for us.
            //Since we are using custom token using JWT we should add ROLE_ prefix
            String token =  jwtTokenProvider.createToken(username, user.getRoles().stream().map(r-> r.getName()).collect(Collectors.toList()));
            return token;

        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new JwtAuthException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
    }

    public boolean signout(String token) {
        jwtTokenClient.delete(new JwtToken(token));
        return true;
    }

    public Boolean isValidToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }

    public String createNewToken(String token) {
        String username = jwtTokenProvider.getUsername(token);
        List<String>roleList = jwtTokenProvider.getRoleList(token);
        String newToken =  jwtTokenProvider.createToken(username,roleList);
        return newToken;
    }
}