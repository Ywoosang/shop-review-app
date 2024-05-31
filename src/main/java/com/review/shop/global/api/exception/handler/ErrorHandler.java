package com.review.shop.global.api.exception.handler;

import com.review.shop.global.api.code.BaseErrorCode;
import com.review.shop.global.api.exception.GeneralException;

public class ErrorHandler extends GeneralException{
    public ErrorHandler(BaseErrorCode errorCode) { super(errorCode); }
}
