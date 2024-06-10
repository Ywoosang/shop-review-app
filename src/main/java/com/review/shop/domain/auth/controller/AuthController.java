package com.review.shop.domain.auth.controller;

import com.review.shop.domain.auth.converter.AuthConverter;
import com.review.shop.domain.auth.service.AuthService;
import com.review.shop.domain.user.model.User;
import com.review.shop.global.api.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.review.shop.domain.auth.dto.AuthRequestDTO.*;
import static com.review.shop.domain.auth.dto.AuthResponseDTO.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth", description = "인증 관련 API")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary="로그인", description = "accessToken 을 발급할 때 사용한다.")
    public CommonResponse<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO request
    ) {
        String accessToken = authService.login(request);
        return CommonResponse.success(AuthConverter.toLoginResponseDTO(accessToken));
    }

    @PostMapping
    @Operation(summary="회원가입", description = "새로운 사용자를 등록할 때 사용한다.")
    public CommonResponse<SignUpResponseDTO> signUp(
            @RequestBody @Valid SignUpRequestDTO request
    ) {
        User user = authService.signUp(request);
        return CommonResponse.success(AuthConverter.toSignUpDTO(user));
    }
}
