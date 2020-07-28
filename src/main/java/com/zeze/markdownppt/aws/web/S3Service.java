package com.zeze.markdownppt.aws.web;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.zeze.markdownppt.aws.domain.DownloadedMultipartFile;
import com.zeze.markdownppt.aws.web.dto.URLRequestDTO;

@Service
public class S3Service {
    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
            // .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(this.region)
            .build();
    }

    public String upload(MultipartFile multipartFile, String dirName) {
        String fileName = multipartFile.getOriginalFilename();
        String filePath = dirName + "/" + fileName;

        File file = convert(multipartFile);

        s3Client.putObject(new PutObjectRequest(bucket, filePath, file)
            .withCannedAcl(CannedAccessControlList.PublicRead));

        file.deleteOnExit();

        return s3Client.getUrl(bucket, fileName).toString();
    }

    public MultipartFile downloadFile(URLRequestDTO requestDTO) throws IOException {
        URL url = new URL(requestDTO.getUrl());
        // File file = new File(requestDTO.getTitle());
        InputStream inputStream = url.openStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[16384];
        int read;

        while ((read = inputStream.read(bytes, 0, bytes.length)) != -1) {
            outputStream.write(bytes, 0, read);
        }

        return new DownloadedMultipartFile(outputStream.toByteArray(), requestDTO.getTitle());
    }

    private File convert(MultipartFile multipartFile) {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        // tmpDir + generateFileName(multipartFile.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException ie) {
        }
        return convertedFile;
    }

    private String generateFileName(String fileName) {
        int extensionIndex = fileName.lastIndexOf(".");
        String name = fileName.substring(0, extensionIndex);
        String extension = fileName.substring(extensionIndex + 1);
        String eightDigitUuid = UUID.randomUUID().toString().split("-")[0];
        return name + "-" + eightDigitUuid + "." + extension;
    }

    private BufferedImage resize(BufferedImage image, int height, int width) {
        Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
