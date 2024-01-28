package com.tasty.app.module.review.form;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewForm {

    // 제목
    private String title;
    // 내용
    private String content;
    // 가게이름
    private String storeName;
    // 가게주소
    private String storeAddress;
    // 평점
    private Integer star;
}
