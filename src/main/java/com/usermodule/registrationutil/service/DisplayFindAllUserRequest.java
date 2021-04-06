package com.usermodule.registrationutil.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisplayFindAllUserRequest {
    private long id;
    private String firstName;
    private String email;
    private String number;
}
