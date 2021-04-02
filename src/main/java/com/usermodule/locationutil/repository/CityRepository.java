package com.usermodule.locationutil.repository;

import com.usermodule.locationutil.entity.City;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Long> {
    List<City> findCitiesByState_Id(Long stateId);
}


