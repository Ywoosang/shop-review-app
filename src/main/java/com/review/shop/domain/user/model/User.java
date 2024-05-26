package com.review.shop.domain.user.model;

import com.review.shop.common.model.BaseEntity;
import com.review.shop.domain.review.model.Review;
import com.review.shop.domain.user.enums.Gender;
import com.review.shop.domain.user.enums.SocialProvider;
import com.review.shop.domain.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "UNIQUE_EMAIL",
                columnNames = {"email"}
        )
})
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    private Integer age;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private SocialProvider provider;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    @ColumnDefault("'ACTIVE'")
    private UserStatus status;

    private LocalDate inactiveDate;

    @ColumnDefault("0")
    private Integer point;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserFoodCategory> userFoodCategories = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}
