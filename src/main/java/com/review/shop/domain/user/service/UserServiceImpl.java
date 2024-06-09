package com.review.shop.domain.user.service;

import com.review.shop.domain.user.model.User;
import com.review.shop.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }
}
