package com.ecom.app.gatewayservice.feignclient;

import com.ecom.app.gatewayservice.auth.JwtToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "account-service")
public interface JwtTokenClient {

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<JwtToken> saveToken(@RequestBody JwtToken jwtToken);

    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    public ResponseEntity<Boolean>  getToken(@PathVariable String token);

    @RequestMapping(value = "/token", method = RequestMethod.DELETE)
    public ResponseEntity<JwtToken> delete(@RequestBody JwtToken jwtToken);
}
