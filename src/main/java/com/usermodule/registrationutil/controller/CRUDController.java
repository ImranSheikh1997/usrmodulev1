package com.usermodule.registrationutil.controller;

import com.usermodule.registrationutil.dto.UpdateRequest;
import com.usermodule.registrationutil.dto.registration.RegistrationRequest;
import com.usermodule.registrationutil.entity.user.User;
import com.usermodule.registrationutil.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CRUDController {

    @Autowired
    private UserService userService;

    @Autowired
    private UpdateRequest updateRequest;

    //This Api will Delete User From DB Based on Email.
    @DeleteMapping(value="/{username}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.delete}", authorizations = { @Authorization(value="apiKey")})
    @ApiResponses(value ={//
            @ApiResponse(code=400, message = "Something Went wrong"),//
            @ApiResponse(code= 403, message = "Access Denied"),//
            @ApiResponse(code = 404, message = "The user doesn't exist"),//
            @ApiResponse(code=500, message = "Expired or invalid token")
    })
    public String delete(
            @ApiParam("email")
            @PathVariable String email){

        userService.delete(email);
        return email;
    }

    @GetMapping("/findalluser")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAllUser(){
        Iterable<User> userList=userService.findAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/finduser/{email}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findUser(
            @PathVariable String email
    ){
        return new ResponseEntity<>(userService.findByEmail(email),HttpStatus.OK);
    }

    @PutMapping("/updateuser/{email}")
    public ResponseEntity<?> updateUser(
            @PathVariable("email") String email,
            @RequestBody(required = false)
                    RegistrationRequest registrationRequest
    )
    {
        updateRequest.updateUser(email,registrationRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
