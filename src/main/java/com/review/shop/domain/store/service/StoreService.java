package com.review.shop.domain.store.service;

import com.review.shop.domain.mission.model.Mission;
import com.review.shop.domain.review.model.Review;
import com.review.shop.domain.store.dto.StoreRequestDTO;
import com.review.shop.domain.store.enums.SortType;
import com.review.shop.domain.store.model.Store;

import java.util.Optional;
import java.util.List;

public interface StoreService {
    // command
    Store createStore(StoreRequestDTO.CreateStoreRequestDTO request);

    Review createStoreReview(Long storeId, StoreRequestDTO.CreateStoreReviewRequestDTO request);

    // query
    Optional<Store> findStore(Long storeId);

//    List<Mission> findStoreMissions(Long storeId);

//    List<Review> findStoreReviews(Long storeId);

    List<Store> findStores(Long lastId, int pageSize, SortType sortType);
}
