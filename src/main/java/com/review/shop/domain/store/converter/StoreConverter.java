package com.review.shop.domain.store.converter;

import com.review.shop.domain.review.model.Review;
import com.review.shop.domain.store.dto.StoreRequestDTO;
import com.review.shop.domain.store.model.Region;
import com.review.shop.domain.store.model.Store;
import java.util.List;

import static com.review.shop.domain.store.dto.StoreRequestDTO.*;
import static com.review.shop.domain.store.dto.StoreResponseDTO.*;

public class StoreConverter {

    // Entity -> ResponseDTO
    public static CreateStoreResponseDTO toCreateStoreDTO(Store store) {
        return CreateStoreResponseDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static FindStoreResponseDTO toFindStoreDTO(Store store) {
        StoreRegionResponseDTO region = StoreRegionResponseDTO.builder()
                .id(store.getRegion().getId())
                .name(store.getRegion().getName())
                .build();

        return FindStoreResponseDTO.builder()
                .storeId(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .score(store.getScore())
                .region(region)
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .build();
    }

    public static FindStoreReviewsResponseDTO toFindStoreReviewsDTO(List<Review> reviews) {
         List<StoreReviewResponseDTO> reviewDTOs = reviews.stream()
                .map(review -> StoreReviewResponseDTO.builder()
                        .reviewId(review.getId())
                        .content(review.getContent())
                        .score(review.getScore())
                        .createdAt(review.getCreatedAt())
                        .userId(review.getUser().getId())
                        .userName(review.getUser().getName())
                        .build()
                ).toList();
         return FindStoreReviewsResponseDTO.builder()
                 .reviews(reviewDTOs)
                 .build();
    }

    public static CreateStoreReviewResponseDTO toCreateStoreReviewDTO(Review review) {
        return CreateStoreReviewResponseDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static FindStoreListResponseDTO toFindStoreListDTO(List<Store> stores) {
        List<StoreOverviewResponseDTO> storeDTOs = stores.stream()
                .map(store -> StoreOverviewResponseDTO.builder()
                        .storeId(store.getId())
                        .name(store.getName())
                        .score(store.getScore())
                        .regionName(store.getRegion().getName())
                        .build()
                ).toList();
        return FindStoreListResponseDTO.builder()
                .stores(storeDTOs)
                .build();
    }


    // requestDTO -> Entity
    public static Store toStore(CreateStoreRequestDTO request) {

        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }



}
