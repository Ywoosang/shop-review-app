package com.review.shop.domain.user.model;

import com.review.shop.global.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "UNIQUE_FOOD_CATEGORY_NAME",
                columnNames = {"name"}
        )
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class FoodCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder
    private FoodCategory(String name) {
        this.name = name;
    }
}