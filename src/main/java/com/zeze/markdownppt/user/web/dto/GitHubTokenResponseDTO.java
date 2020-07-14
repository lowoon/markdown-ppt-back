package com.zeze.markdownppt.user.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GitHubTokenResponseDTO {
    @JsonProperty("access_token")
    private String accessToken;
}
