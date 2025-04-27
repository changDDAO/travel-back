package com.project.travel.domain.user.service;

import com.project.travel.common.error.exceptions.ConflictException;
import com.project.travel.common.error.exceptions.NotFoundException;
import com.project.travel.domain.auth.service.AuthService;
import com.project.travel.domain.user.dto.request.UserUpdateNicknameRequest;
import com.project.travel.domain.user.dto.request.UserUpdatePasswordRequest;
import com.project.travel.domain.user.dto.response.UserProfileResponse;
import com.project.travel.domain.user.entity.User;
import com.project.travel.domain.user.error.UserErrorCode;
import com.project.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthService authService;
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserProfileResponse findUserProfile(Long userId) {
        User user = findById(userId);

        return UserProfileResponse.from(user);
    }

    @Transactional
    public void updateNickname(Long userId, UserUpdateNicknameRequest request) {
        if (userRepository.existsByNickname(request.nickname())) {
            throw new ConflictException(UserErrorCode.ALREADY_REGISTERED_NICKNAME);
        }

        User user = findById(userId);
        user.updateNickname(request.nickname());
    }

    @Transactional
    public void updatePassword(Long userId, UserUpdatePasswordRequest request) {
        User user = findById(userId);

        authService.isSamePassword(request.oldPassword(), user.getPassword());

        if (bCryptPasswordEncoder.matches(request.newPassword(), user.getPassword())) {
            throw new ConflictException(UserErrorCode.NEW_PASSWORD_MATCHES_CURRENT_PASSWORD);
        }

        String newPassword = bCryptPasswordEncoder.encode(request.newPassword());
        user.updatePassword(newPassword);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(UserErrorCode.USER_NOT_FOUND));
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(UserErrorCode.USER_NOT_FOUND));
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
