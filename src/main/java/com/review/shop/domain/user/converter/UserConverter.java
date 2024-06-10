package com.review.shop.domain.user.converter;

import com.review.shop.domain.user.dto.UserResponseDTO.FindUserResponseDTO;
import com.review.shop.domain.user.model.User;

public class UserConverter {
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
}
