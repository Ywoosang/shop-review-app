package com.review.shop.domain.mission.repository;

import com.review.shop.domain.mission.model.Mission;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MissionRepository {
    private final EntityManager em;

    public void create(Mission mission) {
        em.persist(mission);
    }
}
