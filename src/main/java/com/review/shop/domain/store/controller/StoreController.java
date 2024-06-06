package com.review.shop.domain.store.controller;

import com.review.shop.domain.review.model.Review;
import com.review.shop.domain.store.converter.StoreConverter;
import com.review.shop.domain.store.dto.StoreRequestDTO;
import com.review.shop.domain.store.model.Store;
import com.review.shop.domain.store.service.StoreService;
import com.review.shop.global.api.CommonResponse;
import com.review.shop.global.validation.annotation.common.IsExist;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.review.shop.domain.store.dto.StoreRequestDTO.*;
import static com.review.shop.domain.store.dto.StoreResponseDTO.*;
import static com.review.shop.global.api.code.status.ErrorStatus.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
@Tag(name = "Store", description = "가게 관련 API")
public class StoreController {
    private final StoreService storeService;

    @PostMapping
    @Operation(summary="가게 생성", description = "새로운 가게를 등록할 때 사용한다.")
    public CommonResponse<CreateStoreResponseDTO> createStore(
            @RequestBody @Valid CreateStoreRequestDTO request
    ) {
        Store store = storeService.createStore(request);
        return CommonResponse.success(StoreConverter.toCreateStoreDTO(store));
    }


    @PostMapping("/{storeId}/reviews")
    @Operation(summary="가게 리뷰 생성", description = "가게 리뷰를 등록할 때 사용한다.")
    public CommonResponse<CreateStoreReviewResponseDTO> createStoreReviews(
            @PathVariable("storeId") @IsExist(entity = Store.class, errorStatus = STORE_NOT_FOUND) Long storeId,
            @RequestBody @Valid CreateStoreReviewRequestDTO request
    ) {
        Review storeReview = storeService.createStoreReview(storeId, request);
        return CommonResponse.success(StoreConverter.toCreateStoreReviewDTO(storeReview));
    }


    @GetMapping("/{storeId}/reviews")
    @Operation(summary="가게 리뷰 목록 조회", description = "가게 리뷰 목록을 조회할 때 사용한다.")
    public CommonResponse<FindStoreReviewsResponseDTO> findStoreReviews(
            @PathVariable("storeId") @IsExist(entity = Store.class, errorStatus = STORE_NOT_FOUND)   Long storeId
    ) {
        List<Review> reviews = storeService.findStoreReviews(storeId);
        return CommonResponse.success(StoreConverter.toFindStoreReviewsDTO(reviews));
    }


    @GetMapping("/{storeId}")
    @Operation(summary="가게 정보 조회", description = "가게에 대한 정보를 조회할 때 사용한다.")
    public CommonResponse<FindStoreResponseDTO> findStore(
            @PathVariable("storeId") @IsExist(entity = Store.class, errorStatus = STORE_NOT_FOUND) Long storeId
    ) {
        Store store = storeService.findStore(storeId).get();
        return CommonResponse.success(StoreConverter.toFindStoreDTO(store));
    }
}
