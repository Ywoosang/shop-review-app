package com.review.shop.domain.user.dto;

import com.review.shop.domain.user.enums.Gender;
import com.review.shop.domain.user.enums.SocialProvider;
import com.review.shop.domain.user.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UserResponseDTO {


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindUserResponseDTO {

        @Schema(description = "사용자 ID", example = "1")
        private Long userId;

        @Schema(description = "이메일", example = "user@example.com")
        private String email;

        @Schema(description = "이름", example = "윤우상")
        private String name;

        @Schema(description = "성별", example = "MALE")
        private Gender gender;

        @Schema(description = "나이", example = "25")
        private Integer age;

        @Schema(description = "주소", example = "강동구 고덕로 80길 99")
        private String address;

        @Schema(description = "소셜 로그인 제공자", example = "GOOGLE")
        private SocialProvider provider;

        @Schema(description = "사용자 상태", example = "ACTIVE")
        private UserStatus status;

        @Schema(description = "포인트", example = "0")
        private Integer point;

        @Schema(description = "계정 생성 시각")
        private LocalDateTime createdAt;

        @Schema(description = "계정 수정 시각")
        private LocalDateTime updatedAt;
    }
}
