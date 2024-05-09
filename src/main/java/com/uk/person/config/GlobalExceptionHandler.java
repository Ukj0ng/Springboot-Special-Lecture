package com.uk.person.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice   // global exception을 관리
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalAccessException.class)
    public String 요청잘못(IllegalAccessException e) {
        System.out.println(e.getMessage());
        return "오류" + e.getMessage();
    }
}
