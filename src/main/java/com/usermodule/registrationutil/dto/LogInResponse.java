package com.usermodule.registrationutil.dto;

import com.usermodule.registrationutil.dto.login.LogInRequest;
import com.usermodule.registrationutil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInResponse {

    @Autowired
    private UserService userService;
    public String login(LogInRequest logInRequest) {
        return userService.signin(logInRequest.getEmail(),logInRequest.getPassword());
    }
}
