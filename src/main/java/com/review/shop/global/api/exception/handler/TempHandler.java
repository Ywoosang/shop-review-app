package com.review.shop.global.api.exception.handler;

import com.review.shop.global.api.code.BaseErrorCode;
import com.review.shop.global.api.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
