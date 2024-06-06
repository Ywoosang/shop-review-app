package com.review.shop.global.api.exception;

import com.review.shop.global.api.CommonResponse;
import com.review.shop.global.api.code.dto.ErrorReasonDTO;
import com.review.shop.global.api.code.status.ErrorStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    // @PathVariable 검증 실패시
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();

        e.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.merge(fieldName, errorMessage, (existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
        });

        log.error("Constraint violations: {}", errors); // 로그 출력

        CommonResponse<Object> body = CommonResponse.failure(ErrorStatus.BAD_REQUEST.getCode(), "입력값이 올바르지 않습니다.", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // @RequestBody 검증 실패시
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new LinkedHashMap<>();

        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> {
                    String fieldName = fieldError.getField();
                    String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
                    errors.merge(fieldName, errorMessage, (existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
                });

        CommonResponse<Object> body = CommonResponse.failure(ErrorStatus.BAD_REQUEST.getCode(), "입력값이 올바르지 않습니다.", errors);
//        log.error("Validation errors: {}", errors); // 로그 출력
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(value = GeneralException.class)
    protected ResponseEntity<Object> onThrowException(GeneralException generalException, HttpServletRequest request) {
        ErrorReasonDTO errorReasonHttpStatus = generalException.getErrorReasonHttpStatus();
        log.error("GeneralException: {}", errorReasonHttpStatus); //
        return handleExceptionInternal(generalException,errorReasonHttpStatus,null,request);
    }

    private ResponseEntity<Object> handleExceptionInternalConstraint(Exception e, ErrorStatus errorCommonStatus,
                                                                     HttpHeaders headers, WebRequest request) {
        CommonResponse<Object> body = CommonResponse.failure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(), null);
        return new ResponseEntity<>(body, headers, errorCommonStatus.getHttpStatus());
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception e, ErrorReasonDTO reason,
                                                           HttpHeaders headers, HttpServletRequest request) {
        CommonResponse<Object> body = CommonResponse.failure(reason.getCode(),reason.getMessage(),null);
        log.error("Exception: {}", reason);
        WebRequest webRequest = new ServletWebRequest(request);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                reason.getHttpStatus(),
                webRequest
        );
    }
}
