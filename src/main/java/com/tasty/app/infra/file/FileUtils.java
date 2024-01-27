package com.tasty.app.infra.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUtils {

    // 파일 업로드: 1개
    // uploadDirPath: 파일 업로드 위치
    // multipartFile: 업로드 파일
    // return: 업로드 파일 이름
    public String uploadFile(String uploadDirPath, MultipartFile multipartFile) {
        // 업로드 폴더 생성
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 업로드 파일 이름
        String originalFileName = multipartFile.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uploadFileName = UUID.randomUUID() + extension;

        // 파일 업로드
        try {
            multipartFile.transferTo(new File(uploadDirPath + uploadFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return uploadFileName;
    }
}
