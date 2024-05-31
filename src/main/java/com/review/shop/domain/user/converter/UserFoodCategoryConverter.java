package com.review.shop.domain.user.converter;

import com.review.shop.domain.user.model.FoodCategory;
import com.review.shop.domain.user.model.UserFoodCategory;
import java.util.List;

public class UserFoodCategoryConverter {
    public static List<UserFoodCategory> toUserFoodCategoryList(List<FoodCategory> foodCategoryList) {
        return foodCategoryList
                .stream()
                .map(foodCategory -> {
                    UserFoodCategory userFoodCategory = UserFoodCategory.builder().build();
                    userFoodCategory.addFoodCategory(foodCategory);
                    return userFoodCategory;
                }).toList();
    }
}
