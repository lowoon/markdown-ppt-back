package com.zeze.markdownppt.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.zeze.markdownppt.user.web.UserService;
import com.zeze.markdownppt.user.web.dto.CodeRequestDTO;
import com.zeze.markdownppt.user.web.dto.GitHubTokenRequestDTO;

@WebFluxTest(controllers = UserService.class)
class UserServiceTest {

    @Autowired
    private WebTestClient webClient;

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    @Test
    void getToken() {
        CodeRequestDTO codeRequestDTO = new CodeRequestDTO("af9fc7f506ea4f2d6435");

        GitHubTokenRequestDTO gitHubTokenRequestDTO = GitHubTokenRequestDTO.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .code(codeRequestDTO.getCode())
            .build();

        webClient.post()
            .uri("https://github.com/login/oauth/access_token")
            .accept(MediaType.APPLICATION_JSON)
            .syncBody(gitHubTokenRequestDTO)
            .exchange()
            .expectStatus().isOk()
            .expectBody();
    }
}
