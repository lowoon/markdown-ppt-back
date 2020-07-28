package com.zeze.markdownppt.aws.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zeze.markdownppt.aws.web.dto.URLRequestDTO;
import com.zeze.markdownppt.aws.web.dto.URLResponseDTO;
import com.zeze.markdownppt.common.DefaultResponseDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UploadController {
    private final S3Service s3Service;

    @Value("${cloud.aws.dir}")
    private String dir;

    @CrossOrigin("*")
    @PostMapping("/upload/url")
    public ResponseEntity<DefaultResponseDTO<URLResponseDTO>> uploadUrl(
        @RequestBody URLRequestDTO requestDTO) throws IOException {
        MultipartFile file = s3Service.downloadFile(requestDTO);
        String imgPath = s3Service.upload(file, dir);
        URLResponseDTO responseDTO = new URLResponseDTO(imgPath);

        return ResponseEntity.ok(DefaultResponseDTO.from(responseDTO));
    }

    @CrossOrigin("*")
    @PostMapping("/upload/file")
    public ResponseEntity<DefaultResponseDTO<URLResponseDTO>> uploadFile(
        @RequestParam("data") MultipartFile multipartFile) throws IOException {
        // File file = s3Service.downloadFile(requestDTO);
        String imgPath = s3Service.upload(multipartFile, dir);
        URLResponseDTO responseDTO = new URLResponseDTO(imgPath);

        return ResponseEntity.ok(DefaultResponseDTO.from(responseDTO));
    }
}
