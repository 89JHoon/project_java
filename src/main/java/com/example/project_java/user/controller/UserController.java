package com.example.project_java.user.controller;

import com.example.project_java.user.dto.SignUpReqDto;
import com.example.project_java.user.dto.SignUpResDto;
import com.example.project_java.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResDto>signUp(@RequestBody SignUpReqDto signUpReqDto) {
        userService.signUp(signUpReqDto);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }

}
