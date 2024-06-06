package com.review.shop.domain.store.service;

import com.review.shop.domain.store.model.Region;
import com.review.shop.domain.store.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService{
    private final RegionRepository regionRepository;

    public Optional<Region> findRegion(Long id) {
        return regionRepository.findById(id);
    }
}
