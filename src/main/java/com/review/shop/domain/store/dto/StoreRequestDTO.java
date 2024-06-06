package com.review.shop.domain.store.dto;

import com.review.shop.domain.store.model.Store;
import com.review.shop.domain.user.model.User;
import com.review.shop.global.api.code.status.ErrorStatus;
import com.review.shop.global.validation.annotation.common.IsExist;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;

public class StoreRequestDTO {
    @Getter
    public static class CreateStoreRequestDTO {
        @Schema(description = "가게 이름", example = "테스트 가게")
        @NotBlank(message = "가게 이름은 필수 항목입니다.")
        @Size(max = 100, message = "가게 이름은 최대 100자까지 가능합니다.")
        private String name;

        @Schema(description = "가게 주소", example = "수원시 영통구 영통동")
        @NotBlank(message = "가게 주소는 필수 항목입니다.")
        @Size(max = 200, message = "가게 주소는 최대 200자까지 가능합니다.")
        private String address;

        @Schema(description = "지역 ID", example = "1")
        @NotNull(message = "지역 ID는 필수 항목입니다.")
        @IsExist(
                entity = Store.class,
                errorStatus = ErrorStatus.STORE_NOT_FOUND
        )
        private Long regionId;
    }

    @Getter
    public static class UpdateStoreRequestDTO {
        @Schema(description = "가게 이름", example = "테스트 가게")
        @NotEmpty(message = "가게 이름은 필수 항목입니다.")
        @Size(max = 100, message = "가게 이름은 최대 100자까지 가능합니다.")
        private String name;

        @Schema(description = "가게 주소", example = "수원시 영통구 영통동")
        @Size(max = 200, message = "가게 주소는 최대 200자까지 가능합니다.")
        private String address;

        @Schema(description = "지역 ID", example = "2")
        private Long regionId;
    }

    @Getter
    public static class CreateStoreReviewRequestDTO {
        @IsExist(
                entity = User.class,
                errorStatus = ErrorStatus.USER_NOT_FOUND
        )
        @Schema(description = "사용자 ID", example = "1")
        private Long userId;

        @Schema(description = "리뷰 내용", example = "음식이 맛있어요 :)")
        @NotEmpty(message = "내용은 필수 항목입니다.")
        private String content;

        @Schema(description = "리뷰 평점", example = "5")
        @Max(value = 5)
        @Min(value= 1)
        private Long score;
    }
}
