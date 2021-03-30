package com.usermodule.dto.registration;

import com.usermodule.entity.user.User;
import com.usermodule.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegistrationResponse {


    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    //for saving user to the db.
    public void register(RegistrationRequest registrationRequest) {

        log.info("User {}", modelMapper.map(registrationRequest, User.class));
        userService.signUpUser(
                modelMapper.map(registrationRequest, User.class)
        );

    }
}