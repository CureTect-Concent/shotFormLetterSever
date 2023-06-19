package com.shotFormLetter.sFL.domain.post.s3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import java.io.File;

@Service
@RequiredArgsConstructor
public class s3Service {

    private final S3Client s3Client;


    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadImage(File file, String key,String userId) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        PutObjectResponse response = s3Client.putObject(request, file.toPath());

        // 업로드된 파일의 주소 생성
        String imageUrl = generateImageUrl(bucketName, key, userId);
        return imageUrl;
    }

    private String generateImageUrl(String bucketName, String key,String userId) {
        // S3에서 업로드된 파일의 주소 생성
        String imageUrl = "https://" + bucketName + ".s3.ap-northeast-2.amazonaws.com/"+  key;

        return imageUrl;
    }
}