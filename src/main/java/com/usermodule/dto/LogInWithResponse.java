package com.usermodule.dto;

import com.usermodule.dto.registration.RegistrationRequest;
import com.usermodule.entity.user.User;
import com.usermodule.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInWithResponse {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    public void signUp(RegistrationRequest registrationRequest) {
        userService.facebookSignUp(modelMapper.map(registrationRequest, User.class));
    }
}
