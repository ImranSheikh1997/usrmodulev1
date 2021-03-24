package com.usermodule.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LogInRequest {
    String email;
    String password;
}
