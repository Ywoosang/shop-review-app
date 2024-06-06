package com.review.shop.domain.user.service;

import com.review.shop.domain.user.model.FoodCategory;

import java.util.Optional;

public interface FoodCategoryService {
    Optional<FoodCategory> findOne(Long id);
}
