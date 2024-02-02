package com.tasty.app.module.review.repository;

import com.tasty.app.module.review.domain.Review;
import com.tasty.app.infra.dto.Pageable;

import java.util.List;

public interface ReviewRepository {

    // 리뷰 등록
    Long saveReview(Review review);

    // 리뷰 조회
    Review findReviewById(Long reviewId);

    // 리뷰 목록
    List<Review> findAll(int sortOption, String search, Pageable pageable);

    // 리뷰 수정 (DB에서 변경된 행 수 반환)
    Long edit(Long reviewId, Review review);

    // 리뷰 삭제 (DB에서 변경된 행 수 반환)
    Long delete(Long reviewId);
}
