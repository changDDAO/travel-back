package com.project.travel.domain.plan.entity;

import com.project.travel.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "plans")
public class Plan extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "plan_id")
    private Long id;
}
