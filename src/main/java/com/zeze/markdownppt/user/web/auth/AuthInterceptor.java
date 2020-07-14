package com.zeze.markdownppt.user.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final Authenticator authenticator;

    public AuthInterceptor(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) {
        authenticator.setAuthentication(request);
        return true;
    }
}
