package com.shotFormLetter.sFL.domain.s3.controller;

import com.shotFormLetter.sFL.domain.s3.service.s3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequiredArgsConstructor
public class s3Controller {
    private final s3Service s3Service;


    @PostMapping("/public/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        // 파일 이름을 키로 사용하거나 고유한 키를 생성하여 사용할 수 있습니다.
        String key = file.getOriginalFilename();
        try {
            s3Service.uploadImage(convertMultipartFileToFile(file), key);
            // 업로드 성공 시 처리 로직 추가
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            // 업로드 실패 시 처리 로직 추가

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        }
        return file;
    }
}
