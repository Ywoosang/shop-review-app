package com.review.shop.domain.auth.service;

import com.review.shop.domain.auth.converter.AuthConverter;
import com.review.shop.domain.auth.security.model.CustomUserDetails;
import com.review.shop.domain.auth.utils.JwtUtil;
import com.review.shop.domain.user.converter.UserConverter;
import com.review.shop.domain.user.converter.UserFoodCategoryConverter;
import com.review.shop.domain.user.enums.Gender;
import com.review.shop.domain.user.enums.SocialProvider;
import com.review.shop.domain.user.model.FoodCategory;
import com.review.shop.domain.user.model.User;
import com.review.shop.domain.user.model.UserFoodCategory;
import com.review.shop.domain.user.repository.FoodCategoryRepository;
import com.review.shop.domain.user.repository.UserRepository;
import com.review.shop.global.api.code.status.ErrorStatus;
import com.review.shop.global.api.exception.handler.ErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.review.shop.domain.auth.dto.AuthRequestDTO.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public String login(LoginRequestDTO request) {
        String email = request.getEmail();
        String password = request.getPassword();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return jwtUtil.createToken(userDetails);
    };

    @Override
    @Transactional
    public User signUp(SignUpRequestDTO request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

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

        User newUser = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .name(request.getName())
                .gender(gender)
                .age(request.getAge())
                .address(request.getAddress())
                .provider(provider)
                .build();

        List<FoodCategory> foodCategories = request
                .getFoodCategoryIds()
                .stream()
                .map(foodCategoryId -> foodCategoryRepository.findById(foodCategoryId).orElseThrow(() -> new ErrorHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND)))
                .toList();
        List<UserFoodCategory> userFoodCategories = UserFoodCategoryConverter.toUserFoodCategoryList(foodCategories);
        userFoodCategories.forEach(newUser::addUserFoodCategory);
        return userRepository.save(newUser);
    }
}
