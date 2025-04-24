package com.project.travel.domain.auth.dto.response;

public record AuthSignInResponse(
        String accessToken,

        String refreshToken
) {
}
