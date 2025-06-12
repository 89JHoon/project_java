# Spring Boot JWT 인증/인가 백엔드 과제

간결하고 실전적인 사용자 인증/인가 API 백엔드 예제입니다.
Spring Boot, JWT, JUnit, Swagger(OpenAPI), AWS EC2 배포까지 모두 경험할 수 있습니다.

## 프로젝트 소개
기술스택: Java 17+, Spring Boot 3.x, Spring Security, JWT, JUnit, Swagger (springdoc-openapi)

## 주요 목적:
회원가입/로그인/관리자 권한 부여 API 구현
JWT 기반 인증/인가
Swagger UI로 API 문서 자동화
AWS EC2 배포 실습
데이터 저장: 인메모리(별도 DB/파일 미사용)

## 주요 기능
회원가입: 사용자 등록, 중복 체크
로그인: JWT Access Token 발급
관리자 권한 부여: ADMIN 역할 부여 (ADMIN만 가능)
JWT 인증/인가: 토큰 서명, 만료, 권한 검증
API 문서화: Swagger UI 제공

## Swagger UI 접속
http://localhost:8080/swagger-ui.html
![img.png](img.png)