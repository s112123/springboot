package com.tasty.app.module.good.repository;

import com.tasty.app.module.good.domain.Good;
import com.tasty.app.module.good.repository.mapper.GoodMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class GoodRepository {

    private final GoodMapper goodMapper;

    // 찜 등록
    public Long save(Good good) {
        // 찜하기
        goodMapper.insertOne(good);
        // 리뷰 찜 개수 올리기
        goodMapper.increaseGood(good.getReviewId());
        return good.getGoodId();
    }

    // 찜 조회
    public Good findByEmailAndReviewId(Good good) {
        return goodMapper.selectOneByEmailAndReviewId(good);
    }

    // 찜 삭제
    public Long delete(String email, Long reviewId) {
        // 찜 삭제
        Long res = goodMapper.deleteOne(email, reviewId);
        // 리뷰 찜 개수 내리기
        goodMapper.decreaseGood(reviewId);
        return res;
    }
}
