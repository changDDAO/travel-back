package com.project.travel.domain.auth.service;

import com.project.travel.common.error.exceptions.ConflictException;
import com.project.travel.common.security.JwtTokenProvider;
import com.project.travel.domain.auth.dto.request.AuthSignUpRequest;
import com.project.travel.domain.auth.dto.response.AuthSignUpResponse;
import com.project.travel.domain.auth.error.AuthErrorCode;
import com.project.travel.domain.user.entity.User;
import com.project.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthSignUpResponse signUp(AuthSignUpRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ConflictException(AuthErrorCode.AUTH_DUPLICATED_EMAIL);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(request.password());
        User user = request.toEntity(encodedPassword);
        User savedUser = userRepository.save(user);

        String accessToken = jwtTokenProvider.generateAccessToken(String.valueOf(savedUser.getId()), savedUser.getRole().getValue());
        String refreshToken = jwtTokenProvider.generateRefreshToken();

        // add to redis

        return new AuthSignUpResponse(accessToken, refreshToken);
    }
}
