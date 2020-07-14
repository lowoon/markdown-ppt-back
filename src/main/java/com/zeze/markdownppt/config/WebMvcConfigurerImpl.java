package com.zeze.markdownppt.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zeze.markdownppt.user.web.auth.AuthInterceptor;
import com.zeze.markdownppt.user.web.auth.LoginUserArgumentResolver;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    public WebMvcConfigurerImpl(AuthInterceptor authInterceptor,
        LoginUserArgumentResolver loginUserArgumentResolver) {
        this.authInterceptor = authInterceptor;
        this.loginUserArgumentResolver = loginUserArgumentResolver;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //모든 요청에 대해서
            .allowedOrigins("http://localhost:3000");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
            .addPathPatterns("/me")
            .addPathPatterns("/me/*");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
