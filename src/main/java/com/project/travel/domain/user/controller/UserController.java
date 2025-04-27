package com.project.travel.domain.user.controller;

import com.project.travel.common.security.entity.CustomUserDetails;
import com.project.travel.domain.user.dto.response.UserProfileResponse;
import com.project.travel.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserProfileResponse> findUserProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UserProfileResponse response = userService.findUserProfile(userDetails.getId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
