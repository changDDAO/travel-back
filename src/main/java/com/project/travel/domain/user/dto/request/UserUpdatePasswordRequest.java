package com.project.travel.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdatePasswordRequest(
        @NotBlank
        String oldPassword,

        @NotBlank
        String newPassword
) {
}
