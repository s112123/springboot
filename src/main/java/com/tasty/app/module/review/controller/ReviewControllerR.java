package com.tasty.app.module.review.controller;

import com.tasty.app.module.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewControllerR {

    private final ReviewService reviewService;

    // CKEditor 이미지 업로드
    @PostMapping("/upload_image")
    public Map<String, Object> uploadReviewImage(MultipartRequest multipartRequest) {
        MultipartFile multipartFile = multipartRequest.getFile("upload");
        String uploadUrl = reviewService.uploadImage(multipartFile);

        // response
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("uploaded", true);
        responseData.put("url", uploadUrl);
        return responseData;
    }
}
