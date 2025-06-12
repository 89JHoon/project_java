package com.example.project_java.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Schema(description = "로그인 요청 데이터")
public class LoginReqDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Schema(description = "사용자 아이디", example = "user123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userName;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Schema(description = "비밀번호", example = "password123!", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    public LoginReqDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
