package com.example.project_java.user.service;


import com.example.project_java.exception.AuthException;
import com.example.project_java.exception.ErrorCode;
import com.example.project_java.user.dto.*;
import com.example.project_java.user.entity.User;
import com.example.project_java.user.enums.Role;
import com.example.project_java.user.repository.UserRepository;
import com.example.project_java.utils.JwtProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("회원가입 성공 테스트")
    public void signupSuccessTest() {
        // Given
        SignUpReqDto requestDto = new SignUpReqDto("testuser", "password123", "테스트유저");

        when(userRepository.existByUsername(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        User savedUser = new User("testuser", "encodedPassword", "테스트유저");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // When
        SignUpResDto responseDto = userService.signUp(requestDto);

        // Then
        assertNotNull(responseDto);
        assertEquals("testuser", responseDto.getUserName());
        assertEquals("테스트유저", responseDto.getNickname());

        assertNotNull(responseDto.getRoles());
        assertThat(responseDto.getRoles()).hasSize(1);
        assertThat(responseDto.getRoles().get(0).getRole()).isEqualTo(Role.USER);

        verify(userRepository).existByUsername("testuser");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("회원가입 실패 테스트 - 이미 존재하는 사용자")
    public void signupFailureTest() {
        // Given
        SignUpReqDto requestDto = new SignUpReqDto("existinguser", "password123", "기존유저");

        when(userRepository.existByUsername(requestDto.getUserName())).thenReturn(true);

        // When & Then
        AuthException exception = assertThrows(AuthException.class, () -> {
            userService.signUp(requestDto);
        });

        assertEquals(ErrorCode.USER_ALREADY_EXISTS, exception.getErrorCode());
        verify(userRepository).existByUsername(requestDto.getUserName());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() {
        // Given
        LoginReqDto requestDto = new LoginReqDto("testuser", "password123");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtProvider.generateToken(authentication)).thenReturn("test.jwt.token");

        // When
        LoginResDto responseDto = userService.login(requestDto);

        // Then
        assertNotNull(responseDto);
        assertEquals("test.jwt.token", responseDto.getToken());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtProvider).generateToken(authentication);
    }

    @Test
    @DisplayName("로그인 실패 테스트 - 잘못된 인증 정보")
    public void loginFailureTest() {
        // Given
        LoginReqDto requestDto = new LoginReqDto("testuser", "wrongpassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        // When & Then
        assertThrows(BadCredentialsException.class, () -> {
            userService.login(requestDto);
        });

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtProvider, never()).generateToken(any(Authentication.class));
    }

    @Test
    @DisplayName("관리자 권한 부여 성공 테스트")
    public void assignAdminRoleSuccessTest() {
        // Given
        Long userId = 1L;
        User user = new User("testuser", "encodedPassword", "테스트유저");
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        AdminRoleResDto responseDto = userService.assignAdminRole(userId);

        // Then
        assertNotNull(responseDto);
        assertEquals("testuser", responseDto.getUserName());
        assertEquals("테스트유저", responseDto.getNickname());
        verify(userRepository).save(user);
        assertThat(user.getRoles()).contains(Role.ADMIN);

        verify(userRepository).findById(userId);
    }

    @Test
    @DisplayName("관리자 권한 부여 실패 테스트 - 사용자 없음")
    public void assignAdminRoleUserNotFoundTest() {
        // Given
        Long userId = 999L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When & Then
        AuthException exception = assertThrows(AuthException.class, () -> {
            userService.assignAdminRole(userId);
        });

        assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());
        verify(userRepository).findById(userId);
    }
}