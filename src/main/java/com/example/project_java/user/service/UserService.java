package com.example.project_java.user.service;

import com.example.project_java.user.dto.SignUpReqDto;
import com.example.project_java.user.dto.SignUpResDto;
import com.example.project_java.user.entity.User;
import com.example.project_java.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResDto signUp(SignUpReqDto requestDto) {
        User user = new User(requestDto.getUserName(), requestDto.getPassword(), requestDto.getNickname());
        userRepository.save(user);

        return new SignUpResDto(user.getUserName(), user.getNickname(), user.getRoles());
    }
}
