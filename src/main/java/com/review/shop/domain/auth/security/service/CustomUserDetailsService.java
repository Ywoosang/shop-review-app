package com.review.shop.domain.auth.security.service;

import com.review.shop.domain.auth.security.model.CustomUserDetails;
import com.review.shop.domain.user.model.User;
import com.review.shop.domain.user.repository.UserRepository;
import com.review.shop.global.api.code.status.ErrorStatus;
import com.review.shop.global.api.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    // JwtAuthFilter 에서 유효성 검증할 때
    // JWT 에서 추출한 유저 식별자 (email) 과 일치하는 User 가 데이터베이스에 존재하는지 여부를 판단한다.
    // 존재하면 SpringSecurity 에서 내부적으로 사용되는 Auth 객체 (UserPasswordAuthenticationToken) 을
    // 만들때 필요한 UserDetails 객체로 변환하는 역할을 한다.
    // UserDetails 를 확장한 CustomUserDetails 를 사용했다.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          User user = userRepository.findByEmail(email)
                 .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        return CustomUserDetails.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
