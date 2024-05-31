package com.review.shop.domain.user.service;

import com.review.shop.domain.user.dto.UserRequestDTO;
import com.review.shop.domain.user.model.User;

import java.util.Optional;

public interface UserService {
    // command
    User signUp(UserRequestDTO.SignUpDTO request);
}
