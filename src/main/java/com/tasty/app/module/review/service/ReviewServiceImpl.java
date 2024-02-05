package com.tasty.app.module.review.service;

import com.tasty.app.infra.file.FileUtils;
import com.tasty.app.module.good.repository.mapper.GoodMapper;
import com.tasty.app.module.review.domain.Review;
import com.tasty.app.infra.dto.Pageable;
import com.tasty.app.module.review.form.EditForm;
import com.tasty.app.module.review.form.AddForm;
import com.tasty.app.module.review.repository.ReviewRepository;
import com.tasty.app.module.review.repository.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    @Value("${review.upload.dir}")
    private String reviewUploadDir;

    private final ReviewMapper reviewMapper;
    private final GoodMapper goodMapper;
    private final ReviewRepository reviewRepository;
    private final FileUtils fileUtils;

    @Override
    public void addReview(AddForm form) {
        // DTO → Entity
        Review review = Review.toReviewFromAddForm(form);
        Long reviewId = reviewRepository.saveReview(review);
        log.info("reviewId={}", reviewId);
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        return  "/review/image/" + fileUtils.uploadFile(reviewUploadDir, multipartFile);
    }

    @Override
    public Review getReviewById(Long reviewId) {
        // 리뷰 조회
        return reviewRepository.findReviewById(reviewId);
    }

    @Override
    public void increaseHits(Long reviewId) {
        reviewRepository.increaseHits(reviewId);
    }

    @Override
    public List<Review> getReviews(int sortOption, String search, Pageable pageable) {
        // 페이지 조건 전달
        int total = reviewMapper.countAllBySearch(search);
        pageable.setTotal(total);
        return reviewRepository.findAll(sortOption, search, pageable);
    }

    @Override
    public void editReview(Long reviewId, EditForm form) {
        Review review = Review.toReviewFromEditForm(form);
        reviewRepository.edit(reviewId, review);
    }

    @Override
    public void removeReview(Long reviewId) {
        reviewRepository.delete(reviewId);
    }

    @Override
    public List<Review> getReviewsByEmail(String email, Pageable pageable) {
        int total = reviewMapper.countAllByEmail(email);
        pageable.setTotal(total);
        return reviewRepository.findAllByEmail(email, pageable);
    }

    @Override
    public void removeReviews(int[] checkItems) {
        reviewRepository.deleteAll(checkItems);
    }

    @Override
    public List<Map<String, Object>> getReviewsByGoodByEmail(String email, Pageable pageable) {
        int total = goodMapper.countAllByEmail(email);;
        pageable.setTotal(total);
        return reviewRepository.findAllByGoodByEmail(email, pageable);
    }
}
