package com.zeze.markdownppt.aws.web;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.zeze.markdownppt.aws.web.dto.URLRequestDTO;

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
    void upload() throws IOException {
        String filePath = "src/test/resources";
        String fileName = "test-image.jpg";
        File file = new File(String.format("%s/%s", filePath, fileName));
        MockMultipartFile mockMultipartFile = new MockMultipartFile("files", fileName,
            MediaType.IMAGE_JPEG_VALUE, new FileInputStream(file));

        String actual = s3Service.upload(mockMultipartFile, "static");
        String basicURL = String.format("https://%s.s3.ap-northeast-2.amazonaws.com/%s", "markdown-ppt-test", "static");

        assertThat(actual).startsWith(basicURL);
    }
}
