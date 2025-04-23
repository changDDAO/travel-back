package com.project.travel.domain.view.entity;

import com.project.travel.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "views")
public class View extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "view_id")
    private Long id;
}
