package com.project.travel.domain.user.service;

import com.project.travel.common.error.exceptions.NotFoundException;
import com.project.travel.domain.user.dto.response.UserProfileResponse;
import com.project.travel.domain.user.entity.User;
import com.project.travel.domain.user.error.UserErrorCode;
import com.project.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserProfileResponse findUserProfile(Long userId) {
        User user = findById(userId);

        return UserProfileResponse.from(user);
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
