package com.usermodule.registrationutil.controller;

import com.usermodule.registrationutil.dto.login.FileStorageResponse;
import com.usermodule.registrationutil.dto.registration.RegistrationRequest;
import com.usermodule.registrationutil.dto.registration.RegistrationResponse;
import com.usermodule.registrationutil.entity.user.User;
import com.usermodule.registrationutil.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

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

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    //Registration form api
    @PostMapping("/registration")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value={//
            @ApiResponse(code = 500, message = "Interval server error! while uploading Image"), //
            @ApiResponse(code = 403, message = "Access Denied"),//
            @ApiResponse(code = 422, message = "Username is already in use")})
    public void register(
            @ApiParam("Sign up user")
            @RequestBody(required = false) RegistrationRequest registrationRequest){

        registrationRequest.setFilename( fileStorageResponse.convertToMultipart(registrationRequest.getAvatar()));

        User user = registrationResponse.register(registrationRequest);

        String value = String.valueOf(user.isEmailVerified());
        try {
            Thread.sleep(3000);
            if(user.isEmailVerified()){
                log.info("true");
                value = value + "=" + userService.signin(registrationRequest.getEmail(),registrationRequest.getPassword());
                messagingTemplate.convertAndSendToUser(HtmlUtils.htmlEscape(registrationRequest.getEmail()), "/queue/notification", value);
            }
            else{
                log.info("false");
                messagingTemplate.convertAndSendToUser(HtmlUtils.htmlEscape(registrationRequest.getEmail()), "/queue/notification", value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

