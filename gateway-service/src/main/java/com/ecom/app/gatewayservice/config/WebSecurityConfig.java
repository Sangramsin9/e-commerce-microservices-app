package com.ecom.app.gatewayservice.config;

import com.ecom.app.gatewayservice.auth.JwtTokenFilterConfigurer;
import com.ecom.app.gatewayservice.auth.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF
        http.cors().and().csrf().disable();

        // Disable session.
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Allowed end points.
        http.authorizeRequests()
                .antMatchers("/**/signin/**", "/**/signup/**").permitAll()
                // Authenticate everything.
                .anyRequest().authenticated();

        // Failure redirect to login
        http.exceptionHandling().accessDeniedPage("/signin");

        // Apply JWT filter.
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Allow eureka client to be accessed without authentication
        web.ignoring().antMatchers("/*/")//
                .antMatchers("/**/signup/**")
                .antMatchers("/**/signin/**")
                .antMatchers("/eureka/**")//
                .antMatchers(HttpMethod.OPTIONS, "/**"); // Request type options should be allowed.
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

}
