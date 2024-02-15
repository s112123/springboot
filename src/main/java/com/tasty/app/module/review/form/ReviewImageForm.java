package com.tasty.app.module.review.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReviewImageForm {

    private Long reviewId;
    private List<String> uploadFileNames;
}
