package com.usermodule.controller;

import com.usermodule.dto.registration.RegistrationRequest;
import com.usermodule.dto.registration.RegistrationResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
//@Api(tags = "User")
@RequestMapping("/usermodule")
@CrossOrigin("*")
public class RegistrationController {

    @Autowired
    private RegistrationResponse registrationResponse;

    //This Api is for Registration Form
    @PostMapping("/registration")
    @ApiOperation(value = "${UserController.registration}")
    @ApiResponses(value={//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access Denied"),//
            @ApiResponse(code = 422, message = "Username is already in use")})
    public ResponseEntity<?> register(
            @ApiParam("Sign up user")
            @RequestBody RegistrationRequest registrationRequest){

        registrationResponse.register(registrationRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //This Api is for confirming api and enabling user
    @ApiOperation(value = "${UserController.confirm.token}")
    @GetMapping(path ="/registration/confirm")
    public ResponseEntity<?> confirm(
            @RequestParam("token") String token){

        registrationResponse.confirmToken(token);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }




//    @GetMapping(path = "/")
//    public HttpStatus check() {
//        return HttpStatus.OK;
//    }
}

