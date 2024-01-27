package com.tasty.app.module.review.domain;

import com.tasty.app.module.review.form.ReviewForm;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    // PK
    private Long reviewId;
    // FK
    // private Long memberId;
    // 제목
    private String title;
    // 내용
    private String content;
    // 조회 수
    //private Long hit;
    // 등록시간
    // 수정시간

    // DTO(ReviewForm) → Entity(Review)
    public static Review toReviewFromReviewForm(ReviewForm reviewForm) {
        return Review.builder()
                .title(reviewForm.getTitle())
                .content(reviewForm.getContent())
                .build();
    }
}
