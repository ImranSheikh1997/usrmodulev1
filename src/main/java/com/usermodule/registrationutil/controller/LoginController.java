package com.usermodule.registrationutil.controller;

import com.usermodule.registrationutil.dto.LogInResponse;
import com.usermodule.registrationutil.dto.login.LogInRequest;
import com.usermodule.registrationutil.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/usermodule")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogInResponse logInResponse;

    //This Api is for  Signing form
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {
            @ApiResponse(code = 400,message = "Something Went Wrong"), //
            @ApiResponse(code = 422,message = "Invalid username/password supplied")
    }
    )
    @PostMapping("/signin")
    public ResponseEntity<?> login(
            @RequestBody LogInRequest logInRequest) {
        return new ResponseEntity<>(logInResponse.login(logInRequest), HttpStatus.ACCEPTED);
    }

}
