package com.review.shop.domain.user.converter;


import com.review.shop.domain.user.dto.UserRequestDTO;
import com.review.shop.domain.user.dto.UserResponseDTO;
import com.review.shop.domain.user.enums.Gender;
import com.review.shop.domain.user.enums.SocialProvider;
import com.review.shop.domain.user.model.User;

public class UserConverter {
    // Entity -> ResponseDTO
    public static UserResponseDTO.SignUpDTO toSignUpDTO(User user) {
        return UserResponseDTO.SignUpDTO.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    // requestDTO -> Entity
    public static User toUser(UserRequestDTO.SignUpDTO request) {
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
