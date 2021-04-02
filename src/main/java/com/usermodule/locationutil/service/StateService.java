package com.usermodule.locationutil.service;

import com.usermodule.locationutil.entity.State;
import com.usermodule.locationutil.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StateService {

    private final StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public Iterable<State> getStatesByCountry(Long countryId){
        return stateRepository.findStatesByCountry_Id(countryId);
    }

    public Optional<State> findStateById(Long stateId){
        return stateRepository.findById(stateId);
    }
}
