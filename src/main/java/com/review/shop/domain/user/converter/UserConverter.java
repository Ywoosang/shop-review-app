package com.review.shop.domain.user.converter;

import com.review.shop.domain.user.dto.UserRequestDTO.SignUpRequestDTO;
import com.review.shop.domain.user.dto.UserResponseDTO.FindUserResponseDTO;
import com.review.shop.domain.user.dto.UserResponseDTO.SignUpResponseDTO;
import com.review.shop.domain.user.enums.Gender;
import com.review.shop.domain.user.enums.SocialProvider;
import com.review.shop.domain.user.model.User;

public class UserConverter {
    // Entity -> ResponseDTO
    public static SignUpResponseDTO toSignUpDTO(User user) {
        return SignUpResponseDTO.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static FindUserResponseDTO toFindUserDTO(User user) {
        return FindUserResponseDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .gender(user.getGender())
                .age(user.getAge())
                .address(user.getAddress())
                .provider(user.getProvider())
                .status(user.getStatus())
                .point(user.getPoint())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }



    // requestDTO -> Entity
    public static User toUser(SignUpRequestDTO request) {
        Gender gender =  switch(request.getGender()) {
            case "MALE" -> Gender.MALE;
            case "FEMALE" -> Gender.FEMALE;
            default -> null;
        };

        SocialProvider provider = switch (request.getProvider()) {
            case "GOOGLE" -> SocialProvider.GOOGLE;
            case "KAKAO" -> SocialProvider.KAKAO;
            case "NAVER" -> SocialProvider.NAVER;
            default -> null;
        };

        return User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .gender(gender)
                .age(request.getAge())
                .address(request.getAddress())
                .provider(provider)
                .build();
    }
}
