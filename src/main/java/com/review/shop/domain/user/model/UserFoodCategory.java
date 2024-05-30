package com.review.shop.domain.user.model;

import com.review.shop.global.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
public class UserFoodCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="food_category_id")
    private FoodCategory foodCategory;

    @Builder
    protected UserFoodCategory() {}

    public void addUser(User user) {
        this.user = user;
    }

    public void addFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }
}

