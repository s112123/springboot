package com.tasty.app.module.good.repository.mapper;

import com.tasty.app.module.good.domain.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodMapper {

    // 찜 등록
    void insertOne(Good good);

    // 찜 조회
    Good selectOneByEmailAndReviewId(Good good);

    // 찜 삭제
    Long deleteOne(@Param("email") String email, @Param("reviewId") Long reviewId);

    // 리뷰 찜 개수 올리기
    void increaseGood(Long reviewId);

    // 리뷰 찜 개수 내리기
    void decreaseGood(Long reviewId);

    // 특정 회원이 찜한 개수
    int countAllByEmail(String email);
}
