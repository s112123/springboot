package com.tasty.app.module.review.controller;

import com.tasty.app.module.review.domain.Review;
import com.tasty.app.infra.page.Pageable;
import com.tasty.app.module.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewControllerR {

    private final ReviewService reviewService;

    // 리뷰 목록
    @GetMapping
    public Map<String, Object> reviews(
           @RequestParam(value = "sort_option") int sortOption,
           @RequestParam(value = "search", defaultValue = "") String search,
           Pageable pageable
    ) {
        // 리뷰 목록 조회
        List<Review> reviews = reviewService.getReviews(sortOption, search, pageable);

        // response
        Map<String, Object> response = new HashMap<>();
        response.put("sortOption", sortOption);
        response.put("search", search);
        response.put("pageable", pageable);
        response.put("reviews", reviews);

        return response;
    }

    // 리뷰 등록시, CKEditor 임시 이미지 업로드
    @PostMapping("/upload_image")
    public Map<String, Object> uploadReviewTempImage(MultipartRequest multipartRequest) {
        MultipartFile multipartFile = multipartRequest.getFile("upload");
        String uploadUrl = reviewService.uploadImage(multipartFile);

        // response
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("uploaded", true);
        responseData.put("url", uploadUrl);

        return responseData;
    }
}
