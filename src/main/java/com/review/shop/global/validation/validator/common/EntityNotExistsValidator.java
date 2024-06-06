package com.review.shop.global.validation.validator.common;

import com.review.shop.global.api.code.status.ErrorStatus;
import com.review.shop.global.api.exception.GeneralException;
import com.review.shop.global.validation.annotation.common.IsNotExist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityNotExistsValidator implements ConstraintValidator<IsNotExist, Object> {
    private final EntityManager em;
    private Class<?> entityClass;
    private String fieldName;
    private ErrorStatus errorStatus;

    @Override
    public void initialize(IsNotExist constraintAnnotation) {
        this.entityClass = constraintAnnotation.entity();
        this.fieldName = constraintAnnotation.field();
        this.errorStatus = constraintAnnotation.errorStatus();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String jpql = String.format("SELECT COUNT(e) FROM %s e WHERE e.%s = :value", entityClass.getSimpleName(), fieldName);
        Query query = em.createQuery(jpql);
        query.setParameter("value", value);

        Long count = (Long) query.getSingleResult();
        boolean isExist = count > 0;
        if (isExist) {
             context.disableDefaultConstraintViolation();
             context.buildConstraintViolationWithTemplate(errorStatus.getMessage()).addConstraintViolation();
        }

        return !isExist;
    };
}
