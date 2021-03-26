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

    public RegistrationRequest(String email, String password, String firstName, String lastName, Gender gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }
}
