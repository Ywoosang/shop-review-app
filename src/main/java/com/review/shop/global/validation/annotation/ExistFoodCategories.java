package com.review.shop.global.validation.annotation;

import com.review.shop.global.validation.validator.FoodCategoriesExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FoodCategoriesExistsValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistFoodCategories {
    String message() default "존재하지 않는 음식 카테고리입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
