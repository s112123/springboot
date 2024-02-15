package com.tasty.app.module.review.repository.mapper;

import com.tasty.app.module.review.domain.Review;
import com.tasty.app.infra.page.Pageable;
import com.tasty.app.module.review.domain.ReviewImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

    // 리뷰 등록
    void insertOne(Review review);

    // 리뷰 조회
    Review selectOneById(Long reviewId);

    // 총 리뷰 수 (검색어)
    int countAllBySearch(String search);

    // 총 리뷰 수 (이메일)
    int countAllByEmail(String email);

    // 리뷰 목록
    List<Review> selectAll(
            @Param("sortOption") int sortOption,
            @Param("search") String search,
            @Param("pageable") Pageable pageable
    );

    // 리뷰 수정
    Long updateOneById(@Param("params") Map<String, Object> params);

    // 리뷰 삭제
    Long deleteOne(Long reviewId);

    // 조회 수 증가
    void updateHits(Long reviewId);

    // 리뷰 목록을 이메일로 조회
    List<Review> selectAllByEmail(@Param("email") String email, @Param("pageable") Pageable pageable);

    // 리뷰 선택 삭제
    Long deleteAll(int[] checkItems);

    // 내가 찜한 리뷰 목록
    List<Map<String, Object>> selectAllByGoodByEmail(@Param("email") String email, @Param("pageable") Pageable pageable);

    // 리뷰 이미지 등록
    void insertAllForReviewImage(@Param("reviewImages") List<ReviewImage> reviewImages);

    // 리뷰 이미지 조회
    List<ReviewImage> selectAllForReviewImages(Long reviewId);

    // 리뷰 파일 목록 삭제
    void deleteAllForReviewImages(Long reviewId);
}
