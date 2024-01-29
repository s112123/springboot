package com.tasty.app.module.review.repository;

import com.tasty.app.module.review.domain.Review;
import com.tasty.app.module.review.dto.Pageable;

import java.util.List;

public interface ReviewRepository {

    // 리뷰 등록
    Long saveReview(Review review);

    // 리뷰 조회
    Review findReviewById(Long reviewId);

    // 리뷰 목록
    List<Review> findAll(int sortOption, Pageable pageable);
}