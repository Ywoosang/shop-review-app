package com.review.shop.domain.mission.service;

import com.review.shop.domain.mission.model.Mission;
import com.review.shop.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements  MissionService{
    private final MissionRepository missionRepository;

    @Override
    public Mission createMission(Mission mission) {
        return null;
    }
}
