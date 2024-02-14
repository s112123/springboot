package com.tasty.app.module.review.service;

import com.tasty.app.infra.file.util.FileUtils;
import com.tasty.app.module.good.repository.mapper.GoodMapper;
import com.tasty.app.module.notification.domain.Notification;
import com.tasty.app.module.notification.service.NotificationService;
import com.tasty.app.module.review.domain.Review;
import com.tasty.app.infra.page.Pageable;
import com.tasty.app.module.review.form.EditForm;
import com.tasty.app.module.review.form.AddForm;
import com.tasty.app.module.review.repository.ReviewRepository;
import com.tasty.app.module.review.repository.mapper.ReviewMapper;
import com.tasty.app.module.subscribe.service.SubscribeService;
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

    @Value("${upload.dir.review}")
    private String uploadDirReview;

    private final ReviewMapper reviewMapper;
    private final GoodMapper goodMapper;
    private final ReviewRepository reviewRepository;
    private final FileUtils fileUtils;
    private final NotificationService notificationService;
    private final SubscribeService subscribeService;

    @Override
    public void addReview(AddForm form) {
        // DTO → Entity
        Review review = Review.toReviewFromAddForm(form);
        Long reviewId = reviewRepository.saveReview(review);

        // 알림 발송
        List<Map<String, Object>> subscribers = subscribeService.getSubscribersToMe(form.getEmail());
        for (Map<String, Object> subscriber : subscribers) {
            Notification notification = Notification.builder()
                    .fromEmail(form.getEmail())
                    .toEmail((String) subscriber.get("subscriber_email"))
                    .category("review")
                    .content(subscriber.get("publisher_nick_name") + "님이 [" + form.getTitle() + "] 리뷰를 등록하였습니다")
                    .url("/review/view?review_id=" + reviewId)
                    .build();

            // 알림 발송
            notificationService.sendNotification((String) subscriber.get("subscriber_email"), notification);
            // 알림 저장
            notificationService.addNotification(notification);
        }
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        return  "/images/review/" + fileUtils.uploadFile(uploadDirReview, multipartFile, false);
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
