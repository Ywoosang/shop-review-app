package com.review.shop.domain.store.repository;

import com.review.shop.domain.store.model.Region;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RegionRepository {
    private final EntityManager em;

    public void create(Region region) {
        em.persist(region);
    }

    public Optional<Region> findById(Long regionId) {
        return Optional.ofNullable(em.find(Region.class, regionId));
    }
}
