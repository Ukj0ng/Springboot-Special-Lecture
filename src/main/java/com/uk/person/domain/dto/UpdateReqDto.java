package com.uk.person.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateReqDto {
    @NotBlank(message = "password를 입력하세요")
    private String password;
    private String phone;
}
