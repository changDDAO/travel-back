package com.project.travel.domain.user.dto.response;

import com.project.travel.domain.user.entity.User;

public record UserProfileResponse(
        String email,

        String nickname
) {

    public static UserProfileResponse from(User user) {
        return new UserProfileResponse(
                user.getEmail(),
                user.getNickname()
        );
    }
}
