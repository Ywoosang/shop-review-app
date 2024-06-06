package com.review.shop.domain.user.service;

import com.review.shop.domain.user.model.FoodCategory;
import com.review.shop.domain.user.repository.FoodCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements  FoodCategoryService{
    private final FoodCategoryRepository foodCategoryRepository;

    public Optional<FoodCategory> findOne(Long id) {
        return foodCategoryRepository.findById(id);
    }

}
