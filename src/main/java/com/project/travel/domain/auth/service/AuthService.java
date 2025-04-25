package com.project.travel.domain.auth.service;

import com.project.travel.common.error.exceptions.ConflictException;
import com.project.travel.common.error.exceptions.UnauthorizedException;
import com.project.travel.common.security.JwtTokenProvider;
import com.project.travel.domain.auth.dto.request.AuthSignInRequest;
import com.project.travel.domain.auth.dto.request.AuthSignUpRequest;
import com.project.travel.domain.auth.dto.response.AuthSignInResponse;
import com.project.travel.domain.auth.dto.response.AuthSignUpResponse;
import com.project.travel.domain.auth.error.AuthErrorCode;
import com.project.travel.domain.auth.repository.AuthRedisRepository;
import com.project.travel.domain.user.entity.User;
import com.project.travel.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthRedisRepository authRedisRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthSignUpResponse signUp(AuthSignUpRequest request) {
        if (userService.existsByEmail(request.email())) {
            throw new ConflictException(AuthErrorCode.AUTH_DUPLICATED_EMAIL);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(request.password());
        User user = request.toEntity(encodedPassword);
        User savedUser = userService.save(user);

        String accessToken
                = jwtTokenProvider.generateAccessToken(String.valueOf(savedUser.getId()), savedUser.getRole().getValue());
        String refreshToken = jwtTokenProvider.generateRefreshToken();

        authRedisRepository.saveRefreshToken(String.valueOf(savedUser.getId()), refreshToken);

        return new AuthSignUpResponse(accessToken, refreshToken);
    }

    public AuthSignInResponse signIn(AuthSignInRequest request) {
        User user = userService.findByEmail(request.email());

        if (!bCryptPasswordEncoder.matches(request.password(), user.getPassword())) {
            throw new UnauthorizedException(AuthErrorCode.AUTH_PASSWORD_MISMATCH);
        }

        String accessToken
                = jwtTokenProvider.generateAccessToken(String.valueOf(user.getId()), user.getRole().getValue());
        String refreshToken = jwtTokenProvider.generateRefreshToken();

        authRedisRepository.saveRefreshToken(String.valueOf(user.getId()), refreshToken);

        return new AuthSignInResponse(accessToken, refreshToken);
    }
}
