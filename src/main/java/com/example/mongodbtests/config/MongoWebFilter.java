package com.example.mongodbtests.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Log4j2
public class MongoWebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        log.info(
                "Logging Request  {} : {}", req.getMethod(),
                req.getRequestURI());
        filterChain.doFilter(request, response);
        log.info(
                "Logging Response: {}",
                res.getContentType());

//        filterChain.doFilter(request,response); https://www.baeldung.com/spring-boot-add-filter
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
