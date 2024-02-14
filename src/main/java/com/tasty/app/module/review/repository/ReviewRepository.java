package com.tasty.app.module.review.repository;

import com.tasty.app.module.review.domain.Review;
import com.tasty.app.infra.page.Pageable;

import java.util.List;
import java.util.Map;

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

    // 조회수 증가
    void increaseHits(Long reviewId);
    
    // 리뷰 목록을 Email로 조회
    List<Review> findAllByEmail(String email, Pageable pageable);

    // 리뷰 선택 삭제
    Long deleteAll(int[] checkItems);

    // 내가 찜한 리뷰
    List<Map<String, Object>> findAllByGoodByEmail(String email, Pageable pageable);
}
