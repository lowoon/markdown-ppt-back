package com.zeze.markdownppt.user.web.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.NativeWebRequest;

public interface Authenticator {
    void setAuthentication(HttpServletRequest request);

    <T> T getAuthentication(NativeWebRequest request, Class<T> tClass);
}
