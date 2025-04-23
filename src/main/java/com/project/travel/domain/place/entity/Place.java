package com.project.travel.domain.place.entity;

import com.project.travel.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "places")
public class Place {

    @Id @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 1000, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String region;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 100, nullable = false)
    private String latlng;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Boolean isDeleted;

}
