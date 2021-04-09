package com.usermodule.registrationutil.service;

import com.usermodule.registrationutil.entity.token.ConfirmationToken;
import com.usermodule.registrationutil.entity.user.User;
import com.usermodule.registrationutil.repository.ConfirmationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;


    public Optional<ConfirmationToken> getToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
         confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public String emailOrMobileVerification(User userId){
        //Generating random number for Email verification token and Sms Verification
        ConfirmationToken confirmationToken = new ConfirmationToken(
                getUniqueToken(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userId
        );
        confirmationTokenRepository.save(confirmationToken);
        return confirmationToken.getToken();
    }

    private String getUniqueToken() {
        return UUID.randomUUID().toString();
    }

    public String findConfirmationTokenByUser(User user) {
        return findConfirmationTokenByUser(user);
    }

    public void deleteTokenByUser(User user) {
        confirmationTokenRepository.deleteByUser(user);
    }

    public User findUserByToken(String token) {
        return confirmationTokenRepository.findUserByToken(token);
    }
}
