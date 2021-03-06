package com.usermodule.registrationutil.controller;

import com.usermodule.registrationutil.displayuserDTO.DisplayUserRequest;
import com.usermodule.registrationutil.dto.UpdateRequest;
import com.usermodule.registrationutil.service.DisplayFindAllUserRequest;
import com.usermodule.registrationutil.service.DisplayUserService;
import com.usermodule.registrationutil.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CRUDController {

    @Autowired
    private UserService userService;

    @Autowired
    private UpdateRequest updateRequest;

    @Autowired
    private DisplayUserService displayUserService;

    //This Api will Delete User From DB Based on Email.
    @DeleteMapping(value="/deleteuser/{email}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.delete}", authorizations = { @Authorization(value="apiKey")})
    @ApiResponses(value ={//
            @ApiResponse(code=400, message = "Something Went wrong"),//
            @ApiResponse(code= 403, message = "Access Denied"),//
            @ApiResponse(code = 404, message = "The user doesn't exist"),//
            @ApiResponse(code=500, message = "Expired or invalid token")
    })
    public ResponseEntity<?> delete(
            @ApiParam("email")
            @PathVariable("email") String email){

        userService.delete(email);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @GetMapping("/findalluser")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAllUser(){
        Iterable<DisplayFindAllUserRequest> userList = displayUserService.get_All_UserProfile();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/finduser/{email}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findUser(
            @PathVariable String email
    ){
        return new ResponseEntity<>(displayUserService.get_UserProfile(email),HttpStatus.OK);
    }

    @PutMapping("/updateuser")
    public ResponseEntity<?> updateUser(
            @RequestBody
            DisplayUserRequest displayUserRequest
    )
    {
        updateRequest.updateUser(displayUserRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
