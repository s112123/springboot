package com.tasty.app.module.review.repository;

import com.tasty.app.module.review.form.ReviewForm;

public interface ReviewRepository {

    // 리뷰 등록
    void saveReview(ReviewForm reviewForm);

    // 리뷰 조회
    ReviewForm findReviewById(Long reviewId);
}
