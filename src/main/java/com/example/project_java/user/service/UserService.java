package com.example.project_java.user.service;

import com.example.project_java.exception.AuthException;
import com.example.project_java.exception.ErrorCode;
import com.example.project_java.user.dto.*;
import com.example.project_java.user.entity.User;
import com.example.project_java.user.enums.Role;
import com.example.project_java.user.repository.UserRepository;
import com.example.project_java.utils.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public SignUpResDto signUp(SignUpReqDto requestDto) {
        if (userRepository.existByUsername(requestDto.getUserName())) {
            throw new AuthException(ErrorCode.USER_ALREADY_EXISTS);
        }
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        User user = new User(requestDto.getUserName(), encodedPassword, requestDto.getNickname());
        userRepository.save(user);
        return SignUpResDto.toDto(user);
    }

    public AdminRoleResDto assignAdminRole(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AuthException(ErrorCode.USER_NOT_FOUND));

        user.updateRoles(Role.ADMIN);

        return AdminRoleResDto.toDto(user);
    }

    public LoginResDto login(LoginReqDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUserName(),
                        loginRequestDto.getPassword()
                )
        );

        String token = jwtProvider.generateToken(authentication);
        return new LoginResDto(token);
    }
}