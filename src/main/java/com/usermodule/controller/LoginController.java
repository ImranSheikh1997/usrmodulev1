package com.usermodule.controller;

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
@RequestMapping("/usermodule")
@CrossOrigin("*")
public class LoginController {


    @Autowired
    private UserService userService;

    //This Api is for  Signing form
    @GetMapping(path="/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {
            @ApiResponse(code = 400,message = "Something Went Wrong"), //
            @ApiResponse(code = 422,message = "Invalid username/password supplied")
    }
    )
    public ResponseEntity<?> login(//
                                  @RequestParam("email") String email, //
                                   @RequestParam("password") String password) {
       //String token = userService.signin(email,password);
        return new ResponseEntity<>(HttpStatus.OK, HttpStatus.ACCEPTED);
    }

}
