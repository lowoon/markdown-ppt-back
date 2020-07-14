package com.zeze.markdownppt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MarkdownPPTApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarkdownPPTApplication.class, args);
    }

}
