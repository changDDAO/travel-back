package com.project.travel.domain.plan.service;

import com.project.travel.domain.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
}
