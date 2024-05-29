package com.review.shop.domain.temp.service;

import com.review.shop.global.api.code.status.ErrorStatus;
import com.review.shop.global.api.exception.handler.TempHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService{
    @Override
    public void CheckFlag(Integer flag) {
        if(flag == 1) throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}
