package com.ecom.app.gatewayservice.config;

import com.ecom.app.gatewayservice.filter.RequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public RequestFilter requestFilter() {
        return new RequestFilter();
    }

}
