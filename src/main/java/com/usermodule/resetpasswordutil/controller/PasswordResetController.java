package com.usermodule.resetpasswordutil.controller;

import com.usermodule.resetpasswordutil.dto.ResetPasswordResponse;
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
public class PasswordResetController {

    @Autowired
    private ResetPasswordResponse resetPasswordResponse;

    @GetMapping("/forgetpassword/{email}")
    public ResponseEntity<?> resetPassword(
            @PathVariable("email") String email
    )
    {
       resetPasswordResponse.resetPassword(email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
