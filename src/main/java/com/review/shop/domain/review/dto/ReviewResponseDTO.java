package com.review.shop.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

public class ReviewResponseDTO {
    @Getter
    @Builder
    public static class FindReviewResponseDTO {
        @Schema(description = "리뷰 ID", example = "1")
        private Long reviewId;

        @Schema(description = "리뷰 내용", example = "맛있어요! ꒰՞⸝⸝⊃ ·̫ <՞꒱")
        private String content;

        @Schema(description = "리뷰 점수", example = "5")
        private Long score;

        @Schema(description = "리뷰 생성일", example = "2024-06-02T09:20:23.716752502")
        private LocalDateTime createdAt;

        @Schema(description = "사용자 이름")
        private String userName;

        @Schema(description = "사용자 ID")
        private Long userId;

        @Schema(description = "가게 ID")
        private Long storeId;
    }
}

