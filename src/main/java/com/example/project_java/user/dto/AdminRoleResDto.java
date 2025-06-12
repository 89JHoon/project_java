package com.example.project_java.user.dto;

import com.example.project_java.user.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public class AdminRoleResDto {

    private final String username;
    private final String nickname;
    private final List<RoleDto> roles;

    public AdminRoleResDto(String username, String nickname, List<RoleDto> roles) {
        this.username = username;
        this.nickname = nickname;
        this.roles = roles;
    }

    public static AdminRoleResDto toDto(User user) {
        List<RoleDto> roles = user.getRoles()
                .stream()
                .map(RoleDto::new)
                .toList();
        return new AdminRoleResDto(user.getUserName(), user.getNickname(), roles);
    }
}
