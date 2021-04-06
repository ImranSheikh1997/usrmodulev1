package com.usermodule.resetpasswordutil.service;

import com.usermodule.exceptionutil.CustomException;
import com.usermodule.registrationutil.entity.user.User;
import com.usermodule.registrationutil.service.UserService;
import com.usermodule.resetpasswordutil.dto.PasswordResetToken;
import com.usermodule.resetpasswordutil.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetTokenService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public String createToken(String email) {
        Optional<User> user = userService.findByEmail(email);
        if(user.isPresent()) {
            String token = UUID.randomUUID().toString();

            PasswordResetToken passwordResetToken = new PasswordResetToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    user.get()
            );
            passwordResetTokenRepository.save(passwordResetToken);
            return token;
        }
        else{
            throw new CustomException("Invalid Email ", HttpStatus.BAD_REQUEST);
        }
    }
}
