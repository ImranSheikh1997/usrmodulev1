package com.usermodule.registrationutil.displayuserDTO;

import com.usermodule.registrationutil.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DisplayUserRequest {
    private String email;
    private String city;
    private String country;
    private String firstName;
    private String lastName;
    private Gender gender;
    @Pattern(regexp="(^$|[0-9]{10})")
    private String number;
    private String state;

}
