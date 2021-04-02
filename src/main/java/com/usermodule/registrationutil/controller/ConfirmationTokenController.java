package com.usermodule.registrationutil.controller;

import com.usermodule.registrationutil.dto.VerificationResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin("*")
public class ConfirmationTokenController {
    @Autowired
    private VerificationResponse verificationResponse;

    //This Api is used for send Email verification link
    @GetMapping("/verifyemail/{email}")
    public ResponseEntity<?> verifyEmail(
            @PathVariable("email") String email
    ){
        verificationResponse.verifyByEmail(email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //This Api is for confirming api and enabling user
    @ApiOperation(value = "${UserController.confirm.token}")
    @GetMapping(path ="/login/confirm/{token}")
    public ResponseEntity<?> confirm(
            @ApiParam("token")
            @PathVariable("token") String token){

        verificationResponse.confirmToken(token);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
