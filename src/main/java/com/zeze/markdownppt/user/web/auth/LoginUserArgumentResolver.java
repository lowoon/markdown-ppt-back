package com.zeze.markdownppt.user.web.auth;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.zeze.markdownppt.user.domain.LoginEmail;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final Authenticator authenticator;

    public LoginUserArgumentResolver(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class) && parameter.getParameterType()
            .equals(LoginEmail.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String email = authenticator.getAuthentication(webRequest, String.class);
        return new LoginEmail(email);
    }
}
