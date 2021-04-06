package com.usermodule.registrationutil.service;

import com.usermodule.registrationutil.displayuserDTO.DisplayUserRequest;
import com.usermodule.registrationutil.entity.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisplayUserService {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    public DisplayUserRequest get_UserProfile(String email) {
        Optional<User> user = userService.findByEmail(email);
        return modelMapper.map(user.get(),DisplayUserRequest.class);
    }

    public Iterable<DisplayFindAllUserRequest> get_All_UserProfile() {
        List<User> users = userService.findAllUser();

        return users
                .stream()
                .map(user -> modelMapper.map(user, DisplayFindAllUserRequest.class))
                .collect(Collectors.toList());

    }


}
