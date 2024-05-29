package com.review.shop.domain.temp.dto;

import lombok.*;

public class TempResponse {

    @Builder
    @Getter
    public static class TempTestDTO{
        String testString;
    }

    @Builder
    @Getter
    public static class TempExceptionDTO{
        Integer flag;
    }
}
