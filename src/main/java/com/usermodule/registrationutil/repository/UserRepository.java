package com.usermodule.registrationutil.repository;

import com.usermodule.registrationutil.entity.user.User;
import com.usermodule.registrationutil.model.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository
    extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.isEmailVerified = TRUE WHERE a.email = ?1")
    void enableUser(String email);

    @Transactional
    void deleteByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a" +
            " SET a.password = ?2 WHERE a.email = ?1")
    void updatePassword(String email, String password);


    @Transactional
    @Modifying
    @Query("UPDATE User a" +
            " SET a.city = ?2, a.country = ?3," +
            " a.firstName = ?4, " +
            " a.lastName = ?5, " +
            "a.gender = ?6," +
            " a.number = ?7, a.state= ?8 where a.email = ?1"
    )
    void updateUserByEmail(String email,
                           String city,
                           String country,
                           String firstName,
                           String lastName,
                           Gender gender,
                           String number,
                           String state);
}
