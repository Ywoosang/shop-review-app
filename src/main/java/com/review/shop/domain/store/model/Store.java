package com.review.shop.domain.store.model;

import com.review.shop.common.model.BaseEntity;
import com.review.shop.domain.mission.model.Mission;
import com.review.shop.domain.review.model.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="region_id", nullable = false)
    private Region region;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}
