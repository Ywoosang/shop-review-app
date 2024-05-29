package com.review.shop.global.api.code.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ReasonDTO {
    private HttpStatus httpStatus;
    private final boolean isSuccess;
    private final String code;
    private final String message;
}
