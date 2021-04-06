package com.usermodule.registrationutil.dto.registration;

import com.usermodule.registrationutil.dto.VerificationResponse;
import com.usermodule.registrationutil.entity.user.User;
import com.usermodule.registrationutil.model.enums.Role;
import com.usermodule.registrationutil.repository.ConfirmationTokenRepository;
import com.usermodule.registrationutil.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegistrationResponse {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationResponse verificationResponse;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    //for saving user to the db.
    public void register(RegistrationRequest registrationRequest) {

        Role roles = Role.valueOf("ROLE_USER");
        registrationRequest.setRole(roles);
        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        User userId = userService.signUpUser(
                new User(
                        registrationRequest.getEmail(),
                        registrationRequest.getPassword(),
                        registrationRequest.getFirstName(),
                        registrationRequest.getLastName(),
                        registrationRequest.getGender(),
                        registrationRequest.getTitle(),
                        registrationRequest.getRole(),
                        registrationRequest.getNumber(),
                        false,
                        registrationRequest.getFilename(),
                        registrationRequest.getCountry(),
                        registrationRequest.getState(),
                        registrationRequest.getCity()
                )
        );
    }

}