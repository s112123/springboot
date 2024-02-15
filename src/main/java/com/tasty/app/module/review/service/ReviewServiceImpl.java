package com.tasty.app.module.review.service;

import com.tasty.app.infra.file.util.FileUtils;
import com.tasty.app.module.good.repository.mapper.GoodMapper;
import com.tasty.app.module.notification.domain.Notification;
import com.tasty.app.module.notification.service.NotificationService;
import com.tasty.app.module.review.domain.Review;
import com.tasty.app.infra.page.Pageable;
import com.tasty.app.module.review.domain.ReviewImage;
import com.tasty.app.module.review.form.EditForm;
import com.tasty.app.module.review.form.AddForm;
import com.tasty.app.module.review.form.ReviewImageForm;
import com.tasty.app.module.review.repository.ReviewRepository;
import com.tasty.app.module.review.repository.mapper.ReviewMapper;
import com.tasty.app.module.subscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    public void addReview(AddForm form, ReviewImageForm imageForm) {
        // DTO → Entity
        Review review = Review.toReviewFromAddForm(form);
        Long reviewId = reviewRepository.saveReview(review);

        // 리뷰 파일 저장
        imageForm.setReviewId(reviewId);
        addReviewFile(imageForm);

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
    public String uploadTempImage(MultipartFile multipartFile) {
        return  "/images/review/" + fileUtils.uploadFile(uploadDirReview, multipartFile, true);
    }

    @Override
    public String renameUploadFileName(String uploadFileName) {
        // 변경 전: 20240215_5329008e-acfa-45e0-bb6f-f6bb410882fe.png
        // 변경 후: 5329008e-acfa-45e0-bb6f-f6bb410882fe.png
        String newUploadFileName = uploadFileName.substring(uploadFileName.indexOf("_") + 1);
        fileUtils.renameFile(uploadDirReview, uploadFileName, newUploadFileName);
        return newUploadFileName;
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
    public void editReview(Long reviewId, EditForm form, ReviewImageForm imageForm) {
        // 1. DB에 있는 기존의 파일이름 조회
        List<ReviewImage> reviewImages = getReviewImageById(reviewId);

        // 2. 기존의 파일이름 목록과 수정된 파일이름 목록을 비교하여 공통된 항목 제외
        List<String> oldFileNames = new ArrayList<>();
        for (ReviewImage reviewImage : reviewImages) {
            oldFileNames.add(reviewImage.getUploadFileName());
        }
        oldFileNames.removeAll(imageForm.getUploadFileNames());

        // 3. 기존의 파일이름 목록에서 공통되지 않은 목록의 파일은 삭제
        for (String oldFileName : oldFileNames) {
            fileUtils.deleteFile(uploadDirReview, oldFileName);
        }

        // 4. DB에 있는 기존의 파일이름 모두 제거
        removeReviewFile(reviewId);

        // 5. 수정된 게시물에 첨부된 파일 이름을 DB에 새로 등록
        imageForm.setReviewId(reviewId);
        addReviewFile(imageForm);

        // 6. 리뷰 내용 수정
        Review review = Review.toReviewFromEditForm(form);
        reviewRepository.edit(reviewId, review);
    }

    @Override
    public void removeReview(Long reviewId) {
        // 리뷰에 등록된 파일 삭제
        List<ReviewImage> reviewImages = getReviewImageById(reviewId);
        for (ReviewImage reviewImage : reviewImages) {
            fileUtils.deleteFile(uploadDirReview, reviewImage.getUploadFileName());
        }

        // 리뷰 삭제
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
        // 리뷰에 등록된 파일 삭제
        for (int checkItem : checkItems) {
            List<ReviewImage> reviewImages = getReviewImageById((long) checkItem);
            for (ReviewImage reviewImage : reviewImages) {
                fileUtils.deleteFile(uploadDirReview, reviewImage.getUploadFileName());
            }
        }

        // 리뷰 삭제
        reviewRepository.deleteAll(checkItems);
    }

    @Override
    public List<Map<String, Object>> getReviewsByGoodByEmail(String email, Pageable pageable) {
        int total = goodMapper.countAllByEmail(email);;
        pageable.setTotal(total);
        return reviewRepository.findAllByGoodByEmail(email, pageable);
    }

    // 리뷰 파일 등록
    private void addReviewFile(ReviewImageForm imageForm) {
        List<ReviewImage> reviewImages = new ArrayList<>();
        for (String uploadFileName : imageForm.getUploadFileNames()) {
            reviewImages.add(
                ReviewImage.builder()
                        .reviewId(imageForm.getReviewId())
                        .uploadFileName(uploadFileName)
                        .build()
            );
        }
        reviewRepository.saveReviewFile(reviewImages);
    }

    // 리뷰에 등록된 파일 조회
    private List<ReviewImage> getReviewImageById(Long reviewId) {
        return reviewRepository.findAllReviewImages(reviewId);
    }

    // 리뷰에 등록된 파일 삭제
    private void removeReviewFile(Long reviewId) {
        reviewRepository.deleteAllForReviewImage(reviewId);
    }
}
