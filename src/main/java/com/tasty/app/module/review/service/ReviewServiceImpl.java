package com.tasty.app.module.review.service;

import com.tasty.app.infra.file.FileUtils;
import com.tasty.app.module.review.form.ReviewForm;
import com.tasty.app.module.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    //@Value("${review.image.dir}")
    private String reviewUploadDir;

    private final ReviewRepository reviewRepository;
    private final FileUtils fileUtils;

    @Override
    public void addReview(ReviewForm reviewForm) {
        reviewRepository.saveReview(reviewForm);
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        return fileUtils.uploadFile(reviewUploadDir, multipartFile);
    }

    @Override
    public ReviewForm getReviewById(Long reviewId) {
        return reviewRepository.findReviewById(reviewId);
    }
}
