package com.zeze.markdownppt.aws.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class URLRequestDTO {
    private String title;
    private String url;
}
