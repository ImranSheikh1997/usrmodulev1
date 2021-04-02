package com.usermodule.locationutil.service;

import com.usermodule.locationutil.entity.Country;
import com.usermodule.locationutil.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;



    public Iterable<Country> findAllCountry() {
        return countryRepository.findAll();
    }


    public Optional<Country> findCountryById(Long countryId){
        return countryRepository.findById(countryId);
    }
}


