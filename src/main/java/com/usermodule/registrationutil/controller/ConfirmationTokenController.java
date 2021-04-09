package com.usermodule.registrationutil.controller;

import com.google.gson.Gson;
import com.usermodule.registrationutil.dto.VerificationResponse;
import com.usermodule.registrationutil.entity.user.User;
import com.usermodule.registrationutil.service.ConfirmationTokenService;
import com.usermodule.registrationutil.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
@Slf4j
@Api(tags = "Email verification Controller")
@CrossOrigin("*")
public class ConfirmationTokenController {
    @Autowired
    private VerificationResponse verificationResponse;

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    //This Api is used for send Email verification link
    @GetMapping("/verifyemail/{email}")
    public ResponseEntity<?> verifyEmail(
            @PathVariable("email") String email
    ){
        verificationResponse.verifyByEmail(email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //This Api is used for otp verification
    @GetMapping("/sendotp/{number}")
    public ResponseEntity<?> verifyByMobileNumber(
            @PathVariable("number") String number
    ){
        verificationResponse.mobileVerification(number);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //This Api is for confirming api and enabling user
    @ApiOperation(value = "${UserController.confirm.token}")
    @GetMapping(path ="/login/confirm/{token}")
    public ResponseEntity<?> confirm(
            @ApiParam("token")
            @PathVariable("token") String token){
        String jwt = verificationResponse.confirmToken(token);
        User user =  confirmationTokenService.findUserByToken(token);
        messagingTemplate.convertAndSendToUser(HtmlUtils.htmlEscape(user.getEmail()), "/queue/notification", jwt);
        String json = new Gson().toJson(jwt);
        log.info("Json value -> ",json);
        return new ResponseEntity<>(json,HttpStatus.ACCEPTED);
    }

}
