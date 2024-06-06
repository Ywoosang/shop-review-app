package com.review.shop.domain.review.repository;

import com.review.shop.domain.review.model.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {
    private final EntityManager em;

    public Review findById(Long id) {
        return em.find(Review.class, id);
    }
    
}
