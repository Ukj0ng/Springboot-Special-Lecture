package com.uk.person.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JoinReqDto {

    @NotNull(message = "username 키값이 없습니다.")
    @NotBlank(message = "username을 입력하세요.")
    @Size(max = 20, message = "username 길이를 초과하였습니다.")
    private String username;

    @NotNull(message = "비밀번호가 없습니다.")
    private String password;

    private String phone;
}
