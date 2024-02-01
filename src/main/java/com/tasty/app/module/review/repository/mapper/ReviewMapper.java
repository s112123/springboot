package com.tasty.app.module.review.repository.mapper;

import com.tasty.app.module.review.domain.Review;
import com.tasty.app.module.review.dto.Pageable;
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

    // 총 리뷰 수
    int countAll();

    // 리뷰 목록
    List<Review> selectAll(@Param("sortOption") int sortOption,
                           @Param("search") String search,
                           @Param("pageable") Pageable pageable);

    // 리뷰 수정
    Long updateOneById(@Param("params") Map<String, Object> params);

    // 리뷰 삭제
    Long deleteOne(Long reviewId);
}
