package com.review.shop.domain.auth.service;

import com.review.shop.domain.user.model.User;

import static com.review.shop.domain.auth.dto.AuthRequestDTO.*;

public interface AuthService {
    public String login(LoginRequestDTO request);
    User signUp(SignUpRequestDTO request);
}
