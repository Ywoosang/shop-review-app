package com.review.shop.domain.mission.model;
import com.review.shop.domain.store.model.Store;
import com.review.shop.domain.user.model.UserMission;
import com.review.shop.global.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
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
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<UserMission> userMissions = new ArrayList<>();

    @Builder
    private Mission(String content, String test, LocalDate deadline, Integer point) {
        this.content = content;
        this.test = test;
        this.deadline = deadline;
        this.point = point;
    }

    public void addStore(Store store) {
        this.store = store;
    }

    public void addUserMission(UserMission userMission) {
        this.userMissions.add(userMission);
        userMission.addMission(this);
    }
}
