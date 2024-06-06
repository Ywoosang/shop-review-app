package com.review.shop.domain.review.service;

import com.review.shop.domain.review.model.Review;
import java.util.Optional;

public interface ReviewService {
   Optional<Review> findReview(Long id);
}
