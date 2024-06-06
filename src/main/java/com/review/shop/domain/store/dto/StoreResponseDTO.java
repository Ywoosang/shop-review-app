package com.review.shop.domain.store.dto;

import com.review.shop.domain.store.model.Region;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

public class StoreResponseDTO {
    @Getter
    @Builder
    public static class CreateStoreResponseDTO {
        @Schema(description = "가게 ID", example = "1")
        private Long storeId;
        @Schema(description = "가게 생성일", example = "2024-06-02T09:20:23.716752502")
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class CreateStoreReviewResponseDTO {
        @Schema(description = "리뷰 ID", example = "1")
        private Long reviewId;

        @Schema(description = "가게 리뷰 생성일", example = "2024-06-02T09:20:23.716752502")
        private LocalDateTime createdAt;
    }


    @Getter
    @Builder
    public static class UpdateStoreResponseDTO {
        @Schema(description = "가게 ID", example = "1")
        private Long storeId;
        @Schema(description = "마지막 정보 업데이트", example = "2024-06-02T09:20:23.716752502")
        private LocalDateTime updatedAt;
    }


    @Getter
    @Builder
    public static class FindStoreResponseDTO {
        @Schema(description = "가게 ID", example = "1")
        private Long storeId;

        @Schema(description = "가게 이름", example = "스프링 문구점")
        private String name;

        @Schema(description = "가게 주소", example = "서울특별시 강남구 테헤란로 123")
        private String address;

        @Schema(description = "가게 평점", example = "4.5")
        private Float score;

        @Schema(description = "지역 정보", implementation = StoreRegionResponseDTO.class)
        private StoreRegionResponseDTO region;

        @Schema(description = "가게 생성일", example = "2024-06-02T09:20:23.716752502")
        private LocalDateTime createdAt;

        @Schema(description = "마지막 정보 업데이트", example = "null")
        private LocalDateTime updatedAt;
    }

    @Getter
    @Builder
    public static class StoreRegionResponseDTO {
        @Schema(description = "지역 ID", example = "1")
        private Long id;

        @Schema(description = "지역 이름", example = "서울특별시")
        private String name;
    }

    @Getter
    @Builder
    public static class FindStoreReviewsResponseDTO {
        @Schema(description = "리뷰 목록", example = "")
        private List<StoreReviewResponseDTO> reviews;
    }

    @Getter
    @Builder
    public static class StoreReviewResponseDTO {
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
    }

    @Getter
    @Builder
    public static class FindStorePhotosResponseDTO {
        @Schema(description = "가게 사진 목록")
        private List<StorePhotoResponseDTO> photos;
    }

    @Getter
    @Builder
    public static class StorePhotoResponseDTO {
        @Schema(description = "사진 ID", example = "1")
        private Long photoId;

        @Schema(description = "사진 URL", example = "http://example.com/photo.jpg")
        private String url;

        @Schema(description = "사진 파일 이름", example = "photo.jpg")
        private String filename;
    }
}
