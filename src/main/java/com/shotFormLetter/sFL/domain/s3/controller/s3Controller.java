//package com.shotFormLetter.sFL.domain.s3.controller;
//
//import com.shotFormLetter.sFL.domain.s3.service.s3Service;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
//@RestController
//@RequiredArgsConstructor
//public class s3Controller {
//    private final s3Service s3Service;
//
//    @ApiOperation("업로드")
//    @PostMapping("public/upload")
//    public ResponseEntity<String> uploadImages(@RequestParam("file") MultipartFile[] files) {
//        StringBuilder responseBuilder = new StringBuilder();
//
//        for (MultipartFile file : files) {
//            String key = file.getOriginalFilename();
//            try {
//                String imageUrl = s3Service.uploadImage(convertMultipartFileToFile(file), key);
//                responseBuilder.append("File uploaded successfully. Image URL: ").append(imageUrl).append("\n");
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
//            }
//        }
//        return ResponseEntity.ok(responseBuilder.toString());
//    }
//
//
////    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
////        String key = file.getOriginalFilename();
////        try {
////            String imageUrl = s3Service.uploadImage(convertMultipartFileToFile(file), key);
////            return ResponseEntity.ok("File uploaded successfully. Image URL: " + imageUrl);
////        } catch (Exception e) {
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
////        }
////    }
//
////    @PostMapping
////    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
////        // 파일 이름을 키로 사용하거나 고유한 키를 생성하여 사용할 수 있습니다.
////        String key = file.getOriginalFilename();
////        try {
////            s3Service.uploadImage(convertMultipartFileToFile(file), key);
////            // 업로드 성공 시 처리 로직 추가
////            return ResponseEntity.ok("File uploaded successfully");
////        } catch (Exception e) {
////            // 업로드 실패 시 처리 로직 추가
////
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
////        }
////    }
//
//    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
//        File file = new File(multipartFile.getOriginalFilename());
//        try (OutputStream os = new FileOutputStream(file)) {
//            os.write(multipartFile.getBytes());
//        }
//        return file;
//    }
//}
