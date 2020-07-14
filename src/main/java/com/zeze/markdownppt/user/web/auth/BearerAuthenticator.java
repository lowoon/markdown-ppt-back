package com.zeze.markdownppt.user.web.auth;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import com.zeze.markdownppt.util.TokenProvider;

@Component
public class BearerAuthenticator implements Authenticator {
    public static final String TOKEN_TYPE = "bearer";
    public static final String LOGIN_USER_EMAIL = "loginUserEmail";

    private final HeaderExtractor headerExtractor;
    private final TokenProvider tokenProvider;

    public BearerAuthenticator(HeaderExtractor headerExtractor,
        TokenProvider tokenProvider) {
        this.headerExtractor = headerExtractor;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void setAuthentication(HttpServletRequest request) {
        String token = headerExtractor.extract(request, TOKEN_TYPE);

        if (token.isEmpty()) {
            return;
        }
        if (tokenProvider.validateToken(token)) {

        }

        String email = tokenProvider.getSubject(token);
        request.setAttribute(LOGIN_USER_EMAIL, email);
    }

    @Override
    public <T> T getAuthentication(NativeWebRequest request, Class<T> tClass) {
        Object rawAuth = request.getAttribute(LOGIN_USER_EMAIL, RequestAttributes.SCOPE_REQUEST);
        T auth = tClass.cast(rawAuth);
        if (Objects.isNull(auth)) {

        }
        return auth;
    }
}
