package com.example.project_java.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Schema(description = "회원가입 요청 데이터")
public class SignUpReqDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Schema(description = "사용자 아이디 (로그인 시 사용)", example = "user123", requiredMode = Schema.RequiredMode.REQUIRED)
    private final String userName;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Schema(description = "비밀번호", example = "password123!", requiredMode = Schema.RequiredMode.REQUIRED)
    private final String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Schema(description = "사용자 닉네임", example = "멋진닉네임", requiredMode = Schema.RequiredMode.REQUIRED)
    private final String nickname;

    public SignUpReqDto(String userName, String password, String nickname) {
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
    }
}
