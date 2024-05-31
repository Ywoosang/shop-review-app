package com.review.shop.domain.user.service;

import com.review.shop.domain.user.converter.UserConverter;
import com.review.shop.domain.user.converter.UserFoodCategoryConverter;
import com.review.shop.domain.user.dto.UserRequestDTO;
import com.review.shop.domain.user.model.FoodCategory;
import com.review.shop.domain.user.model.User;
import com.review.shop.domain.user.model.UserFoodCategory;
import com.review.shop.domain.user.repository.FoodCategoryRepository;
import com.review.shop.domain.user.repository.UserRepository;
import com.review.shop.global.api.code.status.ErrorStatus;
import com.review.shop.global.api.exception.handler.ErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public User signUp(UserRequestDTO.SignUpDTO request) {
        User newUser = UserConverter.toUser(request);
        List<FoodCategory> foodCategories = request
                .getFoodCategoryIds()
                .stream()
                .map(foodCategoryId -> {
                    return foodCategoryRepository.findById(foodCategoryId).orElseThrow(() -> new ErrorHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).toList();
        List<UserFoodCategory> userFoodCategories = UserFoodCategoryConverter.toUserFoodCategoryList(foodCategories);
        userFoodCategories.forEach(newUser::addUserFoodCategory);
        return userRepository.save(newUser);
    }

}
