package com.example.project_java.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "로그인 응답 데이터 (JWT 토큰 포함)")
public class LoginResDto {
    @Schema(description = "발급된 JWT Access Token", example = "eKDIkdfjoakIdkfjpekdkcjdkoIOdjOKJDFOlLDKFJKL")
    private String token;

    public LoginResDto(String token) {
        this.token = token;
    }
}
