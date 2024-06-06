package com.review.shop.domain.store.service;

import com.review.shop.domain.store.model.Region;

import java.util.Optional;

public interface RegionService {
    public Optional<Region> findRegion(Long id);
}
