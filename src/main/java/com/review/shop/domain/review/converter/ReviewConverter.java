package com.review.shop.domain.review.converter;

import com.review.shop.domain.review.dto.ReviewResponseDTO;
import com.review.shop.domain.review.model.Review;

public class ReviewConverter {
    // Entity -> ResponseDTO
    public static ReviewResponseDTO.FindReviewResponseDTO toFindReviewDTO(Review review) {
        return ReviewResponseDTO.FindReviewResponseDTO.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .score(review.getScore())
                .createdAt(review.getCreatedAt())
                .userId(review.getUser().getId())
                .userName(review.getUser().getName())
                .storeId(review.getStore().getId())
                .build();
    }
    // RequestDTO -> Entity

}
