package com.tasty.app.module.review.repository;

import com.tasty.app.module.review.domain.Review;
import com.tasty.app.module.review.form.ReviewForm;
import com.tasty.app.module.review.repository.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {

    private final ReviewMapper reviewMapper;

    // 등록된 reviewId 반환
    @Override
    public Long saveReview(Review review) {
        reviewMapper.insertOne(review);
        return review.getReviewId();
    }

    @Override
    public ReviewForm findReviewById(Long reviewId) {
        return reviewMapper.selectOneById(reviewId);
    }
}
