package com.ecom.app.gatewayservice.auth;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class JwtTokenFilter extends GenericFilterBean {
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        if (token == null) {
            throw new JwtAuthException("null token,request details : " + ((HttpServletRequest) req).getRequestURL(), HttpStatus.BAD_REQUEST);
        }
        if (token != null) {
            if (!jwtTokenProvider.isTokenPresentInDB(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                throw new JwtAuthException("Invalid JWT token", HttpStatus.UNAUTHORIZED);
            }
            try {
                jwtTokenProvider.validateToken(token);
            } catch (JwtException | IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                throw new JwtAuthException("Invalid JWT token", HttpStatus.UNAUTHORIZED);
            }
            Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
            // Check for RBAC.
            processRBAC(request, auth);

            //Set Principal.
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(req, res);

    }

    public void processRBAC(HttpServletRequest request, Authentication authentication) {
        if (authentication.getAuthorities().contains("ROLE_CUSTOMER") && (request.getMethod().equalsIgnoreCase("POST") || request.getMethod().equalsIgnoreCase("DELETE"))) {
            if (request.getRequestURI().contains("product")) {
                throw new InsufficientAuthenticationException("Access denied");
            }
        }
    }

}