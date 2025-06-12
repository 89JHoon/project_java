package com.example.project_java.user.service;

import com.example.project_java.exception.AuthException;
import com.example.project_java.exception.ErrorCode;
import com.example.project_java.user.dto.AdminRoleResDto;
import com.example.project_java.user.dto.SignUpReqDto;
import com.example.project_java.user.dto.SignUpResDto;
import com.example.project_java.user.entity.User;
import com.example.project_java.user.enums.Role;
import com.example.project_java.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResDto signUp(SignUpReqDto requestDto) {
        User user = new User(requestDto.getUserName(), requestDto.getPassword(), requestDto.getNickname());
        userRepository.save(user);

        return SignUpResDto.toDto(user);
    }

    public AdminRoleResDto assignAdminRole(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AuthException(ErrorCode.USER_NOT_FOUND));

        user.updateRoles(Role.ADMIN);

        return AdminRoleResDto.toDto(user);
    }
}