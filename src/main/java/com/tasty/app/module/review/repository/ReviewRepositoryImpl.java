package com.tasty.app.module.review.repository;

import com.tasty.app.module.member.repository.mapper.MemberMapper;
import com.tasty.app.module.review.domain.Review;
import com.tasty.app.infra.page.Pageable;
import com.tasty.app.module.review.domain.ReviewImage;
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

    @Override
    public void increaseHits(Long reviewId) {
        reviewMapper.updateHits(reviewId);
    }

    @Override
    public List<Review> findAllByEmail(String email, Pageable pageable) {
        return reviewMapper.selectAllByEmail(email, pageable);
    }

    @Override
    public Long deleteAll(int[] checkItems) {
        return reviewMapper.deleteAll(checkItems);
    }

    @Override
    public List<Map<String, Object>> findAllByGoodByEmail(String email, Pageable pageable) {
        return reviewMapper.selectAllByGoodByEmail(email, pageable);
    }

    @Override
    public void saveReviewFile(List<ReviewImage> reviewImages) {
        reviewMapper.insertAllForReviewImage(reviewImages);
    }

    @Override
    public List<ReviewImage> findAllReviewImages(Long reviewId) {
        return reviewMapper.selectAllForReviewImages(reviewId);
    }

    @Override
    public void deleteAllForReviewImage(Long reviewId) {
        reviewMapper.deleteAllForReviewImages(reviewId);
    }
}
