package com.usermodule.locationutil.service;

import com.usermodule.locationutil.entity.City;
import com.usermodule.locationutil.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CityService {


    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Iterable<City> getCitiesByState(Long stateId) {
         //   log.info("Cities {} ", cityRepository.findCitiesByState_Id(stateId));
            return cityRepository.findCitiesByState_Id(stateId);
        }
        }
