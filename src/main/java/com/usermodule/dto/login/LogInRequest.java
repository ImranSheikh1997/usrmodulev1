package com.usermodule.dto.login;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class LogInRequest {
    String email;
    String password;
}
