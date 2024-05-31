package com.review.shop.domain.user.dto;


import lombok.Getter;
import java.util.List;

public class UserRequestDTO {
    @Getter
    public static class SignUpDTO {
        String email;
        String name;
        String gender;
        Integer age;
        String address;
        String provider;
        List<Long> foodCategoryIds;
    }
}
