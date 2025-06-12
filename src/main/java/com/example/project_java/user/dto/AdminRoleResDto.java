package com.example.project_java.user.dto;

import com.example.project_java.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "사용자 정보 응답 데이터")
public class AdminRoleResDto {

    @Schema(description = "사용자 아이디", example = "user123")
    private final String userName;
    @Schema(description = "사용자 닉네임", example = "멋진닉네임")
    private final String nickname;
    @Schema(description = "사용자 역할 목록", example = "[\"ADMIN\"]")
    private final List<RoleDto> roles;

    public AdminRoleResDto(String userName, String nickname, List<RoleDto> roles) {
        this.userName = userName;
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
