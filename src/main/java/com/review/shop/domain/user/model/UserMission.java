package com.review.shop.domain.user.model;

import com.review.shop.global.model.BaseEntity;
import com.review.shop.domain.mission.enums.MissionStatus;
import com.review.shop.domain.mission.model.Mission;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Builder
    public UserMission(MissionStatus status) {
        this.status = status;
    }

    public void addUser(User user) {
        this.user = user;
    }

    public void addMission(Mission mission) {
        this.mission = mission;
    }
}
