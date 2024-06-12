package com.review.shop.domain.store.service;

import com.review.shop.domain.mission.model.Mission;
import com.review.shop.domain.review.model.Review;
import com.review.shop.domain.store.converter.StoreConverter;
import com.review.shop.domain.store.dto.StoreRequestDTO;
import com.review.shop.domain.store.enums.SortType;
import com.review.shop.domain.store.model.Region;
import com.review.shop.domain.store.model.Store;
import com.review.shop.domain.store.repository.RegionRepository;
import com.review.shop.domain.store.repository.StoreRepository;
import com.review.shop.domain.user.model.User;
import com.review.shop.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreServiceImpl implements StoreService{
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    // command
    @Override
    @Transactional
    public Store createStore(StoreRequestDTO.CreateStoreRequestDTO request) {
        Region region = regionRepository.findById(request.getRegionId()).get();
        Store newStore = StoreConverter.toStore(request);
        newStore.addRegion(region);
        storeRepository.save(newStore);
        return newStore;
    }

    @Override
    @Transactional
    public Review createStoreReview(Long storeId, StoreRequestDTO.CreateStoreReviewRequestDTO request) {
        Long userId = request.getUserId();
        User user = userRepository.findById(userId).get();
        Store store = storeRepository.findById(storeId).get();
        Review review = Review.builder()
                .content(request.getContent())
                .score(request.getScore())
                .build();
        review.addUser(user);
        store.addReview(review);
        return review;
    }

    @Override
    public Optional<Store> findStore(Long storeId) {
        return storeRepository.findById(storeId);
    }

    // @Override
    // public List<Mission> findStoreMissions(Long storeId) {
//        return storeRepository.findStoreMissions(storeId);
//    }

    @Override
    public List<Store> findStores(Long lastId, int pageSize, SortType sortType) {
        return storeRepository.findStoresWithPagination(lastId, pageSize, sortType);
    }
}
