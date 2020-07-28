package com.zeze.markdownppt.aws.web;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zeze.markdownppt.aws.web.dto.URLRequestDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class S3ServiceTest {
    @Autowired
    private S3Service s3Service;

    @Test
    void download() throws IOException {
        URLRequestDTO urlRequestDTO = new URLRequestDTO("space.jpg",
            "https://scx1.b-cdn.net/csz/news/800/2019/4-space.jpg");

        s3Service.downloadFile(urlRequestDTO);
    }

    @Test
    void upload() {

    }
}
