package com.review.shop.global.api.code;

import com.review.shop.global.api.code.dto.ReasonDTO;

public interface BaseCode {
    public ReasonDTO getReason();
    public ReasonDTO getReasonHttpStatus();
}
