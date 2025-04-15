package com.project.travel.domain.view.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "views")
public class View {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "view_id")
    private Long id;
}
