package com.usermodule.registrationutil.controller;

import com.usermodule.registrationutil.dto.login.FileStorageResponse;
import com.usermodule.registrationutil.dto.registration.RegistrationRequest;
import com.usermodule.registrationutil.dto.registration.RegistrationResponse;
import com.usermodule.registrationutil.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "User")
@RequestMapping("/usermodule")
@CrossOrigin("*")
public class RegistrationController {

    @Autowired
    private RegistrationResponse registrationResponse;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageResponse fileStorageResponse;

    //This Api is for Registration Form
    @PostMapping("/registration")
    @ApiResponses(value={//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access Denied"),//
            @ApiResponse(code = 422, message = "Username is already in use")})
    public ResponseEntity<?> register(
            @ApiParam("Sign up user")
            @RequestBody(required = false) RegistrationRequest registrationRequest){

        registrationRequest.setFilename( fileStorageResponse.convertToMultipart(registrationRequest.getAvatar()));

        registrationResponse.register(registrationRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Api to check whether email is available or not
    @GetMapping("/checkemail/{email}")
    @ApiResponses(value={//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access Denied"),//
            @ApiResponse(code = 422, message = "Username is already in use")})
    public ResponseEntity<?> checkemail(
            @ApiParam("email")
            @PathVariable String email
    ){
        userService.checkEmail(email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}

