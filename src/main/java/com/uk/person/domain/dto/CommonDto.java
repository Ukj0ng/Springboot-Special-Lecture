package com.uk.person.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

//@AllArgsConstructor
//@RequiredArgsConstructor
//@NoArgsConstructor
@Data
public class CommonDto<T> {
    private int statusCode;
    private T data;

    public CommonDto(int statusCode, T data) {
        this.data = data;
        this.statusCode = statusCode;
    }

    public CommonDto(int statusCode) {
        this.statusCode = statusCode;
    }
}
