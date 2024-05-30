package com.review.shop.domain.mission.model;
import com.review.shop.domain.store.model.Store;
import com.review.shop.domain.user.model.UserMission;
import com.review.shop.global.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String test;

    private LocalDate deadline;

    // 미션 성공 시 얻는 포인트
    private Integer point;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissions = new ArrayList<>();
}
