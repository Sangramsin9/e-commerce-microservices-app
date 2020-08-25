package com.ecom.app.accountservice.repository;

import com.ecom.app.accountservice.model.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken,String> {
}
