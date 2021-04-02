package com.usermodule.locationutil.repository;

import com.usermodule.locationutil.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CountryRepository extends
        JpaRepository<Country, Long>
{
}


