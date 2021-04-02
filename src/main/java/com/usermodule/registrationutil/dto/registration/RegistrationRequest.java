package com.usermodule.registrationutil.dto.registration;

import com.usermodule.registrationutil.model.enums.Gender;
import com.usermodule.registrationutil.model.enums.Role;
import com.usermodule.registrationutil.model.enums.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
//@EqualsAndHashCode
@NoArgsConstructor
public class RegistrationRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Title title;
    private String number;
    private String avatar;
    private String filename;
    private String country;
    private String state;
    private String city;
    private Role role;


}
