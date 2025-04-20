package com.project.travel.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum UserRole {

    USER("ROLE_USER"),
    ;

    private final String value;

    public static UserRole of(String role) {
        return Arrays.stream(UserRole.values())
                .filter(userRole -> userRole.getValue().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow();
    }
}
