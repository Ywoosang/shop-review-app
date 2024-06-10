package com.review.shop.global.api.code.status;

import com.review.shop.global.api.code.BaseErrorCode;
import com.review.shop.global.api.code.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // Common Errors
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_500_01", "관리자에게 문의 바랍니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON_400_01", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON_401_01", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON_403_01", "금지된 요청입니다."),
    // Auth Errors
    AUTH_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH_401_01", "비밀번호가 일치하지 않습니다."),

    // User Errors
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_404_01", "존재하지 않는 사용자입니다."),
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_404_02", "존재하지 않는 음식 카테고리입니다."),
    USER_EMAIL_ALREADY_EXIST(HttpStatus.CONFLICT, "USER_409_01", "이미 사용중인 이메일입니다."),

    // Store Errors
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_404_01", "존재하지 않는 가게입니다."),
    STORE_REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_404_O2", "존재하지 않는 지역입니다."),

    // Article Errors
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE_404_01", "존재하지 않는 게시글입니다."),

    // Review Errors
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW_404_01", "존재하지 않는 리뷰입니다."),

    // Mission Errors
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_404_01", "존재하지 않는 미션입니다.");

    // Test Error
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}