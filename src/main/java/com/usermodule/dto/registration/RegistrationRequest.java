package com.usermodule.dto.registration;

import com.usermodule.model.enums.Gender;
import com.usermodule.model.enums.Title;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RegistrationRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Title title;
    private String number;
    private String fileName;
    private String country;
    private String state;
    private String city;
}
