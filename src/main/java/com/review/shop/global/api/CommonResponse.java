package com.review.shop.global.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.review.shop.domain.user.dto.UserResponseDTO;
import com.review.shop.global.api.code.BaseCode;
import com.review.shop.global.api.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class CommonResponse<T> {
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    private final String code;

    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public static <T> CommonResponse<T> success(T result) {
        return new CommonResponse<T>(
                true,
                SuccessStatus.OK.getCode(),
                SuccessStatus.OK.getMessage(),
                result
        );
    }

    public static  <T> CommonResponse<T> failure(String code, String message, T result) {
        return new CommonResponse<>(
                false,
                code,
                message,
                result
        );
    }
}
