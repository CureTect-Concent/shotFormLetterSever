//package com.shotFormLetter.sFL.domain.s3.service;
//
////import com.amazonaws.services.s3.model.PutObjectRequest;
////import lombok.RequiredArgsConstructor;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.stereotype.Service;
////import software.amazon.awssdk.services.s3.S3Client;
////import software.amazon.awssdk.services.s3.model.PutObjectResponse;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//import software.amazon.awssdk.services.s3.model.PutObjectResponse;
//import java.io.File;
//
//@Service
//@RequiredArgsConstructor
//public class s3Service {
//
//    private final S3Client s3Client;
//
//
//    @Value("${cloud.aws.s3.bucket}")
//    private String bucketName;
//
////    public void uploadImage(File imageFile, String key) {
////        PutObjectRequest request = PutObjectRequest.builder()
////                .bucket(bucketName)
////                .key(key)
////                .build();
////
////        PutObjectResponse response = s3Client.putObject(request, imageFile.toPath());
////        // Add additional logic for handling the successful upload
////    }
//
//    public String uploadImage(File imageFile, String key) {
//        PutObjectRequest request = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build();
//
//        PutObjectResponse response = s3Client.putObject(request, imageFile.toPath());
//
//        // 업로드된 파일의 주소 생성
//        String imageUrl = generateImageUrl(bucketName, key);
//
//        // 추가적인 로직 수행
//        // ...
//
//        return imageUrl;
//    }
//
//    private String generateImageUrl(String bucketName, String key) {
//        // S3에서 업로드된 파일의 주소 생성
//        String imageUrl = "https://" + bucketName + ".s3.amazonaws.com/" + key;
//
//        return imageUrl;
//    }
//}
