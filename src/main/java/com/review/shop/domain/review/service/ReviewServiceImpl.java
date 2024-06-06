package com.review.shop.domain.review.service;

import com.review.shop.domain.review.model.Review;
import com.review.shop.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public Optional<Review> findReview(Long id) {
        return Optional.ofNullable(reviewRepository.findById(id));
    }
}
