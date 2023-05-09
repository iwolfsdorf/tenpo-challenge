/*
package com.tenpo.api.controllers.errorhandling;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final AtomicInteger requestCount = new AtomicInteger(0);

    public void checkRateLimit() throws TooManyRequestsException {
        if (requestCount.incrementAndGet() > 3) {
            throw new TooManyRequestsException();
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        checkRateLimit();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        requestCount.decrementAndGet();
        System.out.println("Request and Response is completed");
    }

}*/
