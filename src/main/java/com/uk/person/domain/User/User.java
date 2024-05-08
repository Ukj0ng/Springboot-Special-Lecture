package com.uk.person.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {     // 얘는 여러 객체를 필요로 할 수 있어서 IoC컨테이너에서 관리하게 하면 안됨
    private int id;
    private String username;
    private String password;
    private String phone;
}
