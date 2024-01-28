package com.tasty.app.module.review.service;

import com.tasty.app.infra.file.FileUtils;
import com.tasty.app.module.review.domain.Review;
import com.tasty.app.module.review.dto.Pageable;
import com.tasty.app.module.review.form.ReviewForm;
import com.tasty.app.module.review.repository.ReviewRepository;
import com.tasty.app.module.review.repository.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    @Value("${review.upload.dir}")
    private String reviewUploadDir;

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final FileUtils fileUtils;

    @Override
    public void addReview(ReviewForm reviewForm) {
        // DTO → Entity
        Review review = Review.toReviewFromReviewForm(reviewForm);
        Long reviewId = reviewRepository.saveReview(review);
        log.info("reviewId={}", reviewId);
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        return  "/review/image/" + fileUtils.uploadFile(reviewUploadDir, multipartFile);
    }

    @Override
    public ReviewForm getReviewById(Long reviewId) {
        return reviewRepository.findReviewById(reviewId);
    }

    @Override
    public List<Review> getReviews(int sortOption, Pageable pageable) {
        // 페이지 조건 전달
        int total = reviewMapper.countAll();
        pageable.setTotal(total);
        return reviewRepository.findAll(sortOption, pageable);
    }
}
