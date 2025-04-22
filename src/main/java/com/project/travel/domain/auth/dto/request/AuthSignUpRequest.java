package com.project.travel.domain.auth.dto.request;

import com.project.travel.domain.user.entity.User;
import com.project.travel.domain.user.entity.UserRole;
import jakarta.validation.constraints.NotBlank;

public record AuthSignUpRequest(
        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
        String nickname,

        @NotBlank
        String name,

        @NotBlank
        String phone
) {

    public User toEntity(String encodedPassword) {
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .nickname(nickname)
                .name(name)
                .phone(phone)
                .role(UserRole.USER)
                .build();
    }
}
