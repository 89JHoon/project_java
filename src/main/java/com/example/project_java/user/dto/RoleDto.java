package com.example.project_java.user.dto;

import com.example.project_java.user.enums.Role;
import lombok.Getter;

@Getter
public class RoleDto {

    private Role role;

    public RoleDto(Role role) {
        this.role = role;
    }
}
