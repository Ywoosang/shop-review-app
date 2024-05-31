package com.review.shop.domain.user.controller;

import com.review.shop.domain.user.converter.UserConverter;
import com.review.shop.domain.user.dto.UserRequestDTO;
import com.review.shop.domain.user.dto.UserResponseDTO;
import com.review.shop.domain.user.model.User;
import com.review.shop.domain.user.service.UserService;
import com.review.shop.global.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public ApiResponse<UserResponseDTO.SignUpDTO> signUp(@RequestBody @Valid UserRequestDTO.SignUpDTO signUpDTO) {
        User user = userService.signUp(signUpDTO);
        return ApiResponse.success(UserConverter.toSignUpDTO(user));
    }
}
