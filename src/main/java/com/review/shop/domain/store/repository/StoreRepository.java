package com.review.shop.domain.store.repository;

import com.review.shop.domain.mission.model.Mission;
import com.review.shop.domain.review.model.Review;
import com.review.shop.domain.store.model.Store;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepository {
    private final EntityManager em;

    // command
    public void create(Store store) {
        em.persist(store);
    }

    // query
    public Optional<Store> findById(Long id) {
        return Optional.ofNullable(em.find(Store.class, id));
    }

    public List<Mission> findStoreMissions(Long storeId) {
        String jpql = "SELECT m FROM Mission m WHERE m.store.id = :storeId";
        TypedQuery<Mission> query = em.createQuery(jpql, Mission.class);
        query.setParameter("storeId", storeId);
        return query.getResultList();
    }

    public List<Review> findStoreReviews(Long storeId) {
        String jpql = "SELECT r FROM Review r WHERE r.store.id = :storeId";
        TypedQuery<Review> query = em.createQuery(jpql, Review.class);
        query.setParameter("storeId", storeId);
        return query.getResultList();
    }
}
