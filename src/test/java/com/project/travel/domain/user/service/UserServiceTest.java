package com.project.travel.domain.user.service;

import com.project.travel.domain.user.dto.request.UserUpdateNicknameRequest;
import com.project.travel.domain.user.dto.response.UserProfileResponse;
import com.project.travel.domain.user.entity.User;
import com.project.travel.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    Long userId = 1L;
    String email = "email@email.com";
    String password = "password";
    String nickname = "nickname";
    String name = "name";
    String phone = "phone";
    String updatedNick = "updatedNickname";

    @Test
    @DisplayName("")
    void findUserProfile() {
        // given
        User user = User.builder()
                .id(userId)
                .email(email)
                .nickname(nickname)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // when
        UserProfileResponse response = userService.findUserProfile(userId);

        // then
        assertThat(response.email()).isEqualTo(email);
        assertThat(response.nickname()).isEqualTo(nickname);

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("")
    void updateUserNickname() {
        // given
        UserUpdateNicknameRequest request = new UserUpdateNicknameRequest(updatedNick);

        User user = User.builder()
                .id(userId)
                .nickname(nickname)
                .build();

        when(userRepository.existsByNickname(updatedNick)).thenReturn(false);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // when
        userService.updateNickname(userId, request);

        // then
        assertThat(user.getNickname()).isEqualTo(updatedNick);

        verify(userRepository, times(1)).existsByNickname(updatedNick);
        verify(userRepository, times(1)).findById(userId);
    }
}