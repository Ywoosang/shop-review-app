package com.review.shop.global.validation.annotation.common;

import com.review.shop.global.api.code.status.ErrorStatus;
import com.review.shop.global.validation.validator.common.EntityExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EntityExistsValidator.class)
@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExist {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> entity();
    String field() default "id";
    ErrorStatus errorStatus();
}
