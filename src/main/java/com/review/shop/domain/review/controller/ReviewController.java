package com.review.shop.domain.review.controller;

import com.review.shop.domain.review.converter.ReviewConverter;
import com.review.shop.domain.review.model.Review;
import com.review.shop.domain.review.service.ReviewService;
import com.review.shop.global.api.CommonResponse;
import com.review.shop.global.validation.annotation.common.IsExist;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.review.shop.domain.review.dto.ReviewResponseDTO.*;
import static com.review.shop.global.api.code.status.ErrorStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{reviewId}")
    @Operation(summary="리뷰 조회", description = "등록된 리뷰를 id 를 통해 조회한다.")
    public CommonResponse<FindReviewResponseDTO> findReview(
            @IsExist(entity = Review.class, errorStatus = REVIEW_NOT_FOUND) @PathVariable Long reviewId
    ) {
        Review review = reviewService.findReview(reviewId).get();
        return CommonResponse.success(ReviewConverter.toFindReviewDTO(review));
    }
}
