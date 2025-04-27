package com.project.travel.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateNicknameRequest(
        @NotBlank
        String nickname
) {
}
