package com.review.shop.domain.user.model;

import com.review.shop.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "UNIQUE_FOOD_CATEGORY_NAME",
                columnNames = {"name"}
        )
})
public class FoodCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
