package com.usermodule.registrationutil.displayuserDTO;

import com.usermodule.registrationutil.model.enums.Gender;
import com.usermodule.registrationutil.model.enums.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DisplayUserRequest {
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Title title;
    private String number;
    private String filename;
    private String country;
    private String state;
    private String city;
}
