package com.usermodule.registrationutil.repository;

import com.usermodule.registrationutil.entity.token.ConfirmationToken;
import com.usermodule.registrationutil.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly =  true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    String findConfirmationTokenByUser(User user);

    @Transactional
    @Modifying
    @Query(
            "UPDATE ConfirmationToken c " +
                    "SET c.confirmedAt = ?2 "+
                    "WHERE c.token = ?1")
    void updateConfirmedAt(String token,
                           LocalDateTime  confirmedAt);

    @Query(
            "SELECT user FROM ConfirmationToken where token=?1"
    )
    User findUserByToken(String token);

    @Transactional
    @Modifying
    @Query(
            " DELETE from ConfirmationToken c "+
                    " where c.user = ?1"
    )
    void deleteByUser(User user);


}
