package com.review.shop.global.validation.validator;

import com.review.shop.domain.user.service.FoodCategoryService;
import com.review.shop.global.api.code.status.ErrorStatus;
import com.review.shop.global.validation.annotation.ExistFoodCategories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodCategoriesExistsValidator implements ConstraintValidator<ExistFoodCategories,List<Long>> {
    private final FoodCategoryService foodCategoryService;

    @Override
    public void initialize(ExistFoodCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        }

    @Override
    public boolean isValid(List<Long> foodCategoryIds, ConstraintValidatorContext context) {
        boolean isExist = foodCategoryIds.stream()
                .allMatch(id -> foodCategoryService.findOne(id).isPresent());
        if(!isExist) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.getMessage()).addConstraintViolation();
        }
        return isExist;
    }
}
