package com.tasty.app.module.review.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewForm {

    private Long reviewId;
    private String title;
    private String store;
    private String content;
}
