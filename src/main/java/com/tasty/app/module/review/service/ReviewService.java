package com.tasty.app.module.review.service;

import com.tasty.app.module.review.domain.Review;
import com.tasty.app.module.review.dto.Pageable;
import com.tasty.app.module.review.form.ReviewForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {

    // 리뷰 등록
    void addReview(ReviewForm reviewForm);

    // 리뷰 이미지 업로드 (이미지 요청 경로: /review/image/** 반환)
    String uploadImage(MultipartFile multipartFile);

    // 리뷰 조회
    ReviewForm getReviewById(Long reviewId);

    // 리뷰 목록
    List<Review> getReviews(int sortOption, Pageable pageable);
}
