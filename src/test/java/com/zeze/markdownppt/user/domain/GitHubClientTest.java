package com.zeze.markdownppt.user.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GitHubClientTest {
    @Autowired
    private GitHubClient gitHubClient;

    @Test
    void get() {
        assertAll(
            () -> assertThat(gitHubClient.getClientId()).isEqualTo("acdc1508017c51604113"),
            () -> assertThat(gitHubClient.getClientSecret()).isEqualTo(
                "d254353caa6e43170ee0fef0f3eed24e06b55688")
        );
    }
}
