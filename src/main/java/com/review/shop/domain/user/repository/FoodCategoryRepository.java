package com.review.shop.domain.user.repository;

import com.review.shop.domain.user.model.FoodCategory;
import com.review.shop.domain.user.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FoodCategoryRepository {
    private final EntityManager em;

    public void save(FoodCategory foodCategory) {
        em.persist(foodCategory);
    }

    public Optional<FoodCategory> findById(Long id) {
        FoodCategory foodCategory = em.find(FoodCategory.class, id);
        return Optional.ofNullable(foodCategory);
    }

    public List<FoodCategory> findAll() {
        return em.createQuery("select f from FoodCategory f", FoodCategory.class).getResultList();
    }
}
