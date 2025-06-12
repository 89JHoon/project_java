package com.example.project_java.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ErrorDetails {

    private String code;
    private String message;


}
