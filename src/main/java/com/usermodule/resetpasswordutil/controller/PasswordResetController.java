package com.usermodule.resetpasswordutil.controller;

import com.usermodule.resetpasswordutil.dto.ResetPasswordResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/verifypasswordresettoken/{token}")
    public ResponseEntity<?> verifyPassword(
            @PathVariable("token") String token
    ){

        return new ResponseEntity(resetPasswordResponse.verifyToken(token),HttpStatus.ACCEPTED);
    }

    @PostMapping("/updatepassword")
    public ResponseEntity<?> updatePassword(
            @RequestParam String email,
            @RequestParam String password
    ){
        String jwtToken = resetPasswordResponse.updatePassword(email,password);
        return new ResponseEntity<>(jwtToken,HttpStatus.ACCEPTED);
    }
}
