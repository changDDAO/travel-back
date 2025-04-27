package com.project.travel.domain.user.service;

import com.project.travel.domain.auth.service.AuthService;
import com.project.travel.domain.user.dto.request.UserUpdateNicknameRequest;
import com.project.travel.domain.user.dto.request.UserUpdatePasswordRequest;
import com.project.travel.domain.user.dto.response.UserProfileResponse;
import com.project.travel.domain.user.entity.User;
import com.project.travel.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    AuthService authService;

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    UserService userService;

    Long userId = 1L;
    String email = "email@email.com";
    String password = "oldPassword";
    String nickname = "nickname";

    @Test
    @DisplayName("[GET api/users] - 프로필 조회 성공")
    void findProfile() {
        // given
        User user = User.builder()
                .id(userId)
                .email(email)
                .nickname(nickname)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // when
        UserProfileResponse response = userService.findProfile(userId);

        // then
        assertThat(response.email()).isEqualTo(email);
        assertThat(response.nickname()).isEqualTo(nickname);

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("[PATCH api/users/nickname] - 닉네임 변경 성공")
    void updateNickname() {
        // given
        String newNickname = "newNickname";
        UserUpdateNicknameRequest request = new UserUpdateNicknameRequest(newNickname);

        User user = User.builder()
                .id(userId)
                .nickname(nickname)
                .build();

        when(userRepository.existsByNickname(newNickname)).thenReturn(false);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // when
        userService.updateNickname(userId, request);

        // then
        assertThat(user.getNickname()).isEqualTo(newNickname);

        verify(userRepository, times(1)).existsByNickname(newNickname);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("[PATCH api/users/password] - 비밀번호 변경 성공")
    void updatePassword() {
        // given
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String oldEncodedPassword = "oldEncodedPassword";
        String newEncodedPassword = "newEncodedPassword";

        UserUpdatePasswordRequest request = new UserUpdatePasswordRequest(oldPassword, newPassword);

        User user = User.builder()
                .id(userId)
                .password(oldEncodedPassword)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.encode(newPassword)).thenReturn(newEncodedPassword);

        // when
        userService.updatePassword(userId, request);

        // then
        assertThat(user.getPassword()).isEqualTo(newEncodedPassword);

        verify(userRepository, times(1)).findById(userId);
        verify(authService, times(1)).isSamePassword(oldPassword, oldEncodedPassword);
        verify(bCryptPasswordEncoder, times(1)).matches(newPassword, oldEncodedPassword);
        verify(bCryptPasswordEncoder, times(1)).encode(newPassword);
    }
}