package com.zeze.markdownppt.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TokenProviderTest {
    private TokenProvider tokenProvider;

    @BeforeEach
    void setUp() {
        tokenProvider = new TokenProvider("secretsecretsecretsecretsecretsecretsecret", 3600000L);
    }

    @Test
    void getSubject() {
        String email = "sangbo0229@gmail.com";
        String token = tokenProvider.createToken(email);

        assertThat(tokenProvider.getSubject(token)).isEqualTo(email);
    }
}
