package com.project.travel.domain.auth.dto.request;

public record AuthSignInRequest(
        String email,

        String password
) {
}
