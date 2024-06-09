package com.review.shop.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class AuthResponseDTO {

    @Builder
    @Getter
    public static class LoginResponseDTO {
        @Schema(description = "accessToken", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
        private String accessToken;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpResponseDTO {
        @Schema(description = "사용자 ID", example = "1")
        private Long userId;

        @Schema(description = "계정 생성일", example = "2024-06-02T09:20:23.716752502")
        private LocalDateTime createdAt;
    }
}
