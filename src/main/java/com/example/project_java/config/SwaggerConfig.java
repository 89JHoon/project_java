package com.example.project_java.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Java 과제 API 문서") // API 문서 제목
                .version("v1.0.0") // 문서 버전
                .description("Spring Boot와 JWT를 이용한 사용자 인증 및 권한 관리 API 명세입니다."); // 문서 설명

        // Security Scheme 이름 정의
        String jwtSchemeName = "JWT";

        // API 요청 헤더에 인증 정보 포함 설정
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        // SecuritySchemes 등록 (Components 객체에 등록)
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName) // SecurityRequirement에서 사용한 이름과 일치
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("bearer") // 토큰 스킴은 bearer
                        .bearerFormat("JWT")); // 토큰 형식은 JWT

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement) // 모든 API에 전역적으로 보안 요구사항 추가
                .components(components); // SecurityScheme을 Components에 등록
    }
}
