package com.tasty.app.module.review.form;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddForm {

    // fk
    private String email;
    // 제목
    private String title;
    // 내용
    private String content;
    // 가게이름
    private String storeName;
    // 가게주소
    private String storeAddress;
    // 썸네일 이미지 요청 주소
    private String thumbnailUrl;
    // 썸네일 이미지 파일 이름
    private String thumbnailFileName;
    // 평점
    private Integer star;
}
