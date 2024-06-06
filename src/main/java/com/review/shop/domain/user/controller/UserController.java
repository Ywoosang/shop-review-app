package com.review.shop.domain.user.controller;

import com.review.shop.domain.user.converter.UserConverter;
import com.review.shop.domain.user.model.User;
import com.review.shop.domain.user.service.UserService;
import com.review.shop.global.api.CommonResponse;
import com.review.shop.global.validation.annotation.common.IsExist;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.review.shop.domain.user.dto.UserRequestDTO.*;
import static com.review.shop.domain.user.dto.UserResponseDTO.*;
import static com.review.shop.global.api.code.status.ErrorStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User", description = "사용자 프로필 관련 API")
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary="회원가입", description = "새로운 사용자를 등록할 때 사용한다.")
    public CommonResponse<SignUpResponseDTO> signUp(@RequestBody @Valid SignUpRequestDTO request) {
        User user = userService.signUp(request);
        return CommonResponse.success(UserConverter.toSignUpDTO(user));
    }

    @GetMapping("/{userId}")
    @Operation(summary="사용자 프로필 조회", description = "사용자 프로필 정보를 조회한다.")
    public CommonResponse<FindUserResponseDTO> getUserProfile(
            @IsExist(entity = User.class, errorStatus = USER_NOT_FOUND) @PathVariable Long userId
    ) {

        User user = userService.findOne(userId).get();
        return CommonResponse.success(UserConverter.toFindUserDTO(user));
    }

}
