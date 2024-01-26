package com.tasty.app.module.review.repository.mapper;

import com.tasty.app.module.review.form.ReviewForm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

    // 리뷰 등록
    void insertOne(ReviewForm reviewForm);

    // 리뷰 조회
    ReviewForm selectOneById(Long reviewId);
}
