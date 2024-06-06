package com.review.shop.domain.user.dto;

import com.review.shop.domain.user.model.User;
import com.review.shop.global.api.code.status.ErrorStatus;
import com.review.shop.global.validation.annotation.ExistFoodCategories;
import com.review.shop.global.validation.annotation.common.IsNotExist;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class SignUpRequestDTO {

        @Schema(description = "이메일", example = "user@example.com")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        @NotBlank(message = "이메일은 필수 항목입니다.")
        @Size(max = 50, message = "이메일은 최대 50자까지 가능합니다.")
        @IsNotExist(
                entity = User.class,
                errorStatus = ErrorStatus.USER_EMAIL_ALREADY_EXIST,
                field = "email"
        )
        private String email;

        @Schema(description = "이름", example = "윤우상")
        @NotBlank(message = "이름은 필수 항목입니다.")
        @Size(max = 20, message = "이름은 최대 20자까지 가능합니다.")
        private String name;

        @Schema(description = "성별", example = "MALE")
        @NotBlank(message = "성별은 필수 항목입니다.")
        @Pattern(regexp = "MALE|FEMALE", message = "성별은 MALE 또는 FEMALE 이어야 합니다.")
        private String gender;

        @Schema(description = "나이", example = "25")
        @NotNull(message = "나이는 필수 항목입니다.")
        @Min(value = 0, message = "나이는 0 이상이어야 합니다.")
        @Max(value = 150, message = "나이는 150 이하여야 합니다.")
        private Integer age;

        @Schema(description = "주소", example = "강동구 고덕로 80길 99")
        @Size(max = 100, message = "주소는 최대 100자까지 가능합니다.")
        private String address;

        @Schema(description = "소셜 로그인 방법", example = "GOOGLE")
        @Pattern(regexp = "GOOGLE|FACEBOOK|KAKAO|NAVER", message = "소셜 로그인 방법은 GOOGLE, FACEBOOK, KAKAO, NAVER 중 하나여야 합니다.")
        private String provider;

        @Schema(description = "선호하는 음식 카테고리 id 목록", example = "[1, 2, 3]")
        @ExistFoodCategories(message = "")
        private List<Long> foodCategoryIds;
    }
}

