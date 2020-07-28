package com.zeze.markdownppt.user.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.zeze.markdownppt.user.web.dto.GitHubTokenRequestDTO;
import com.zeze.markdownppt.user.web.dto.GitHubTokenResponseDTO;
import com.zeze.markdownppt.user.web.dto.UserInfoDTO;

@Component
public class GitHubClient {
    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;
    private final WebClient webClient;

    public GitHubClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public GitHubTokenResponseDTO getToken(String code) {
        GitHubTokenRequestDTO gitHubTokenRequestDTO = GitHubTokenRequestDTO.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .code(code)
            .build();

        return webClient.post()
            .uri("https://github.com/login/oauth/access_token")
            .accept(MediaType.APPLICATION_JSON)
            .syncBody(gitHubTokenRequestDTO)
            .retrieve()
            .bodyToMono(GitHubTokenResponseDTO.class)
            .block();
    }

    public UserInfoDTO getInfo(String token) {
        return webClient.get()
            .uri("https://api.github.com/user")
            .header("Authorization", "token " + token)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(UserInfoDTO.class)
            .block();
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
