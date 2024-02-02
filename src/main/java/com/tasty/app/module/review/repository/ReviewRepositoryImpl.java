package com.tasty.app.module.review.repository;

import com.tasty.app.module.member.repository.mapper.MemberMapper;
import com.tasty.app.module.review.domain.Review;
import com.tasty.app.infra.dto.Pageable;
import com.tasty.app.module.review.repository.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {

    private final ReviewMapper reviewMapper;
    private final MemberMapper memberMapper;

    // 등록된 reviewId 반환
    @Override
    public Long saveReview(Review review) {
        reviewMapper.insertOne(review);
        return review.getReviewId();
    }

    @Override
    public Review findReviewById(Long reviewId) {
        return reviewMapper.selectOneById(reviewId);
    }

    @Override
    public List<Review> findAll(int sortOption, String search, Pageable pageable) {
        return reviewMapper.selectAll(sortOption, search, pageable);
    }

    @Override
    public Long edit(Long reviewId, Review review) {
        Map<String, Object> params = new HashMap<>();
        params.put("reviewId", reviewId);
        params.put("review", review);
        return reviewMapper.updateOneById(params);
    }

    @Override
    public Long delete(Long reviewId) {
        return reviewMapper.deleteOne(reviewId);
    }
}
