package com.project.travel.domain.user.controller;

import com.project.travel.common.security.entity.CustomUserDetails;
import com.project.travel.domain.user.dto.request.UserUpdateNicknameRequest;
import com.project.travel.domain.user.dto.request.UserUpdatePasswordRequest;
import com.project.travel.domain.user.dto.response.UserProfileResponse;
import com.project.travel.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserProfileResponse> findProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UserProfileResponse response = userService.findProfile(userDetails.getId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/nickname")
    public ResponseEntity<Void> updateNickname(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid UserUpdateNicknameRequest request
    ) {
        userService.updateNickname(userDetails.getId(), request);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid UserUpdatePasswordRequest request
    ) {
        userService.updatePassword(userDetails.getId(), request);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
