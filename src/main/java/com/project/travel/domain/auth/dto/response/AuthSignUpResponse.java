package com.project.travel.domain.auth.dto.response;

public record AuthSignUpResponse(
        String accessToken,

        String refreshToken
) {
}
