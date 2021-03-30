package com.usermodule.service;

import com.usermodule.entity.token.ConfirmationToken;
import com.usermodule.entity.user.User;
import com.usermodule.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

//    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
//        confirmationTokenRepository.save(confirmationToken);
//    }

    public Optional<ConfirmationToken> getToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
         confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
    public String emailOrMobileVerification(User user){
        //Generating random number for Email verification token and Sms Verification
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenRepository.save(confirmationToken);
        return token;
    }
}
