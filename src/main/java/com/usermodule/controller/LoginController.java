package com.usermodule.controller;

import com.usermodule.dto.login.LogInRequest;
import com.usermodule.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
//@RequestMapping("/usermodule")
@CrossOrigin("*")
public class LoginController {


    @Autowired
    private UserService userService;

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
       //String token = userService.signin(email,password);

        return new ResponseEntity<>(logInRequest.getEmail(), HttpStatus.ACCEPTED);
    }

}
