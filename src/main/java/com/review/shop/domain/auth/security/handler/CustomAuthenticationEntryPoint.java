package com.review.shop.domain.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.review.shop.global.api.CommonResponse;
import com.review.shop.global.api.code.status.ErrorStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j(topic = "UNAUTHORIZATION_EXCEPTION_HANDLER")
@Component
@AllArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        log.error("인증되지 않은 요청입니다.", authException);
        ErrorStatus errorStatus = ErrorStatus.AUTH_UNAUTHORIZED;
        CommonResponse<Object> commonResponse = CommonResponse.failure(errorStatus.getCode(), errorStatus.getMessage(), null);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(commonResponse));
    }
}
