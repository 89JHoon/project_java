package com.example.project_java.user.dto;

import com.example.project_java.user.entity.User;
import com.example.project_java.user.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Schema(description = "회원가입 응답 데이터")
public class SignUpResDto {
    @Schema(description = "사용자 아이디", example = "user123")
    private final String userName;

    @Schema(description = "사용자 닉네임", example = "닉네임닉네임")
    private final String nickname;

    @Schema(description = "사용자 역할", example = "[\"USER\"]")
    private final List<RoleDto> roles;

    public SignUpResDto(String userName, String nickname, List<RoleDto> roles) {
        this.userName = userName;
        this.nickname = nickname;
        this.roles = roles;
    }


    public static SignUpResDto toDto(User user) {
        List<RoleDto> roles = user.getRoles()
                .stream()
                .map(RoleDto::new)
                .toList();
        return new SignUpResDto(user.getUserName(), user.getNickname(), roles);
    }
}
