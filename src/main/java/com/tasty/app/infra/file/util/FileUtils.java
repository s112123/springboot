package com.tasty.app.infra.file.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public String uploadFile(String uploadDirPath, MultipartFile multipartFile, boolean isTemp) {
        // 업로드 폴더 생성
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 업로드 파일 이름
        String originalFileName = multipartFile.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uploadFileName = UUID.randomUUID() + extension;

        // 임시파일이면 앞에 날짜 붙임
        if (isTemp) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_");
            uploadFileName = formatter.format(LocalDate.now()) + uploadFileName;
        }

        // 파일 업로드
        try {
            multipartFile.transferTo(new File(uploadDirPath + uploadFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return uploadFileName;
    }

    // 파일 삭제
    public void deleteFile(String uploadDirPath, String uploadFileName) {
        File deleteFile = new File(uploadDirPath + uploadFileName);
        if (deleteFile.exists()) {
            deleteFile.delete();
        }
    }
}
