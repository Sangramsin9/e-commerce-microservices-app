package com.ecom.app.gatewayservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

import javax.servlet.http.HttpSession;

public class RequestFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpSession httpSession = currentContext.getRequest().getSession();
        Session session = sessionRepository.findById(httpSession.getId());
        if(session != null){
            currentContext.addZuulRequestHeader("Cookie",  session.getId());
            LOGGER.info(session.getId());
        }
        return null;
    }
}
