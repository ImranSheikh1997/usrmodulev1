//package com.usermodule.locationutil.controller;
//
//import com.usermodule.locationutil.entity.Country;
//import com.usermodule.locationutil.service.CityService;
//import com.usermodule.locationutil.service.CountryService;
//import com.usermodule.locationutil.service.StateService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import static org.mockito.Mockito.when;
//
//@ExtendWith({MockitoExtension.class})
//public class CountryStateCityControllerTest {
//
//    @Mock
//    CountryService countryService;
//
//    @Mock
//    StateService stateService;
//
//    @Mock
//    CityService cityService;
//
//    @InjectMocks
//    CountryStateCityController countryStateCityController;
//
//    @Test
//    void findAllCountry() {
//        //Given
//        Country country1 = new Country(1,"ab","india","+91");
//        when(countryService.findAllCountry()).thenReturn()
//    }
//
//    @Test
//    void findCities() {
//    }
//
//    @Test
//    void findStates() {
//    }
//}