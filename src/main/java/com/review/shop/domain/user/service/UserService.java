package com.review.shop.domain.user.service;
import com.review.shop.domain.user.model.User;

import java.util.Optional;

import static com.review.shop.domain.user.dto.UserRequestDTO.*;

public interface UserService {
    // command
    Optional<User> findOne(Long id);
}
