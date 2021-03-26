package com.usermodule.controller;

import com.usermodule.service.CityService;
import com.usermodule.service.CountryService;
import com.usermodule.service.StateService;
import com.usermodule.utility.validator.annotations.StateId;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class CountryStateCityController {


    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private StateService stateService;

    //This APi will Return list of Countries
    @GetMapping("registration/country")
    @ApiOperation(value = "${UserController.country}")
    @ApiResponses(value ={//
            @ApiResponse(code=400, message = "Something Went wrong"),//
            @ApiResponse(code= 403, message = "Access Denied")})
    public ResponseEntity<?> findAllCountry(){
        return new ResponseEntity<>(countryService.findAllCountry(), HttpStatus.OK);
    }



    //This api will return list of City According to State
    @GetMapping(value= "registration/city/{stateId}")
    public ResponseEntity<?> findCities(
            @Valid
            @StateId
            @PathVariable Long stateId
    ){
        return new ResponseEntity<>(cityService.getCitiesByState(stateId),HttpStatus.OK);
    }



    //This api will return list of State According to State
    @GetMapping(value= "registration/state/{countryId}")
    public ResponseEntity<?> findStates(
            @Valid
            @StateId
            @PathVariable Long countryId
    ){
        return new ResponseEntity<>(stateService.getStatesByCountry(countryId),HttpStatus.OK);
    }

}
