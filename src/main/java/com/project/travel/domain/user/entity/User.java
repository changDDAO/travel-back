package com.project.travel.domain.user.entity;

import com.project.travel.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "idx_u_email", columnNames = { "email" }),
                @UniqueConstraint(name = "idx_u_nickname", columnNames = { "nickname" })
        }
)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public User(Long id, String email, String password, String nickname, String name, String phone, UserRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public void updateNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}
