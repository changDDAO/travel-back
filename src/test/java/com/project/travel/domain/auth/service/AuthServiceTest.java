package com.project.travel.domain.auth.service;

import com.project.travel.common.security.JwtTokenProvider;
import com.project.travel.domain.auth.dto.request.AuthSignUpRequest;
import com.project.travel.domain.auth.dto.response.AuthSignUpResponse;
import com.project.travel.domain.user.entity.User;
import com.project.travel.domain.user.entity.UserRole;
import com.project.travel.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AuthService authService;

    @Test
    void signUp() {
        // given
        String email = "email@email.com";
        String password = "password";
        String nickname = "nickname";
        String name = "name";
        String phone = "phone";
        String hashedPassword = "hashedPassword";
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";

        AuthSignUpRequest request = new AuthSignUpRequest(
                email,
                password,
                nickname,
                name,
                phone
        );

        User user = request.toEntity(hashedPassword);

        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(bCryptPasswordEncoder.encode(request.password())).thenReturn(hashedPassword);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtTokenProvider.generateAccessToken(String.valueOf(user.getId()), user.getRole().getValue()))
                .thenReturn(accessToken);
        when(jwtTokenProvider.generateRefreshToken()).thenReturn(refreshToken);

        // when
        AuthSignUpResponse response = authService.signUp(request);

        // then
        assertThat(response.accessToken()).isEqualTo(accessToken);
        assertThat(response.refreshToken()).isEqualTo(refreshToken);

        verify(userRepository, times(1)).existsByEmail(request.email());
        verify(bCryptPasswordEncoder, times(1)).encode(request.password());
        verify(userRepository, times(1)).save(any(User.class));
        verify(jwtTokenProvider, times(1))
                .generateAccessToken(String.valueOf(user.getId()), user.getRole().getValue());
        verify(jwtTokenProvider, times(1)).generateRefreshToken();
    }
}