package com.usermodule.registrationutil.entity.user;

import com.usermodule.registrationutil.model.enums.Gender;
import com.usermodule.registrationutil.model.enums.Role;
import com.usermodule.registrationutil.model.enums.Title;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Email(message = "please enter valid email")
    @NotNull(message = "Email can't be null")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Password can't be null")
    private String password;

    @NotNull(message = "FirstName cant't be null")
    private String firstName;

    @NotNull(message = "LastName cant't be null")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Title title;

    @Enumerated(EnumType.STRING)
    private Role roles;

    //@Pattern(regexp="(^$|[0-9]{10})")
    @Column(unique = true)
    private String number;

    private boolean isEmailVerified=false;

    private String fileName;

    private String country;
    private String state;
    private String city;

    public User(@Email(message = "please enter valid email") @NotNull(message = "Email can't be null") String email, @NotNull(message = "Password can't be null") String password, @NotNull(message = "FirstName cant't be null") String firstName, @NotNull(message = "LastName cant't be null") String lastName, Gender gender, Title title, Role role, String number, boolean isEmailVerified, String fileName, String country, String state, String city) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.title = title;
        this.roles = role;
        this.number = number;
        this.isEmailVerified = isEmailVerified;
        this.fileName = fileName;
        this.country = country;
        this.state = state;
        this.city = city;
    }
}