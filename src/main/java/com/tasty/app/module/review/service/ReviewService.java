package com.tasty.app.module.review.service;

import com.tasty.app.module.review.domain.Review;
import com.tasty.app.infra.page.Pageable;
import com.tasty.app.module.review.form.EditForm;
import com.tasty.app.module.review.form.AddForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    // 리뷰 등록
    void addReview(AddForm form);

    // 리뷰 이미지 업로드 (이미지 요청 경로: /images/review/** 반환)
    String uploadImage(MultipartFile multipartFile);

    // 리뷰 조회
    Review getReviewById(Long reviewId);

    // 리뷰 목록
    List<Review> getReviews(int sortOption, String search, Pageable pageable);

    // 리뷰 수정
    void editReview(Long reviewId, EditForm form);

    // 리뷰 삭제
    void removeReview(Long reviewId);

    // 조회수 증가
    void increaseHits(Long reviewId);

    // 리뷰 목록을 이메일로 조회
    List<Review> getReviewsByEmail(String email, Pageable pageable);

    // 리뷰 선택 삭제
    void removeReviews(int[] checkItems);

    // 내가 찜한 리뷰 목록
    List<Map<String, Object>> getReviewsByGoodByEmail(String email, Pageable pageable);
}
