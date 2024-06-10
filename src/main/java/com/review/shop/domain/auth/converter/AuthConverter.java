package com.review.shop.domain.auth.converter;

import com.review.shop.domain.user.enums.Gender;
import com.review.shop.domain.user.enums.SocialProvider;
import com.review.shop.domain.user.model.User;

import static com.review.shop.domain.auth.dto.AuthRequestDTO.*;
import static com.review.shop.domain.auth.dto.AuthResponseDTO.*;

public class AuthConverter {

    // Entity -> ResponseDTO
    public static SignUpResponseDTO toSignUpDTO(User user) {
        return SignUpResponseDTO.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static LoginResponseDTO toLoginResponseDTO(String accessToken) {
        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .build();
    }
}
