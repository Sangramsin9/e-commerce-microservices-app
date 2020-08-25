package com.ecom.app.gatewayservice.auth;

import com.ecom.app.gatewayservice.feignclient.AccountClient;
import com.ecom.app.gatewayservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthManager implements UserDetailsService {

   @Autowired
   AccountClient accountClient;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = accountClient.getUser(s);
        if (user == null || user.getRole() == null) {
            throw new JwtAuthException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

        boolean enabled = user.getActive() == 1 ? true : false;
        UserPrincipal userDetails = new UserPrincipal(user.getEmail(),user.getPassword(),user.getActive(),
                true, true, enabled,authorities);
        return userDetails;
    }
}
