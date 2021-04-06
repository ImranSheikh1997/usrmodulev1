package com.usermodule.registrationutil.service;

import com.usermodule.registrationutil.entity.token.ConfirmationToken;
import com.usermodule.registrationutil.entity.user.User;
import com.usermodule.registrationutil.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
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
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userId
        );
        confirmationTokenRepository.save(confirmationToken);
        return token;
    }

    public String findConfirmationTokenByUser(User user) {
        return findConfirmationTokenByUser(user);
    }
}
