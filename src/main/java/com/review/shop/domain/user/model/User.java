package com.review.shop.domain.user.model;

import com.review.shop.global.model.BaseEntity;
import com.review.shop.domain.review.model.Review;
import com.review.shop.domain.user.enums.Gender;
import com.review.shop.domain.user.enums.SocialProvider;
import com.review.shop.domain.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicInsert
@DynamicUpdate
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

    @Column(length = 5)
    private Integer age;

    @Column(length = 100)
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
    private List<UserMission> userMissions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserFoodCategory> userFoodCategories = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @Builder
    private User(String email, String name, Gender gender, Integer age, String address, SocialProvider provider, UserStatus status, LocalDate inactiveDate, Integer point) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.provider = provider;
        this.status = status;
        this.inactiveDate = inactiveDate;
        this.point = point;
    }

    public void addUserFoodCategory(UserFoodCategory userFoodCategory) {
        userFoodCategories.add(userFoodCategory);
        userFoodCategory.addUser(this);
    }

    public void addUserMission(UserMission userMission) {
        userMissions.add(userMission);
        userMission.addUser(this);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.addUser(this);
    }
}
