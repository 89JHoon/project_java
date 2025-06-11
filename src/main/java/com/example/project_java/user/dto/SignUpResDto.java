package com.example.project_java.user.dto;

import com.example.project_java.user.enums.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
public class SignUpResDto {
    private final String userName;
    private final String nickname;
    private final List<Role> roles;

    public SignUpResDto(String userName, String nickname, List<Role> roles) {
        this.userName = userName;
        this.nickname = nickname;
        this.roles = roles;
    }
}
