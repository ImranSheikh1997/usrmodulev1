package com.usermodule.registrationutil.dto;

import com.usermodule.registrationutil.dto.registration.RegistrationRequest;
import com.usermodule.registrationutil.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateRequest {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    public void updateUser(String email, RegistrationRequest registrationRequest) {
        //Optional<User> user = userService.findByEmail(email);
//        if(registrationRequest.getAvatar() != null){
//            user.get().getFileName() = registrationRequest.getAvatar();
//        }
    }
}
