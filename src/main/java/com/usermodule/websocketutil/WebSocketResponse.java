package com.usermodule.websocketutil;

import com.usermodule.registrationutil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSocketResponse {
    @Autowired
    private UserService userService;

    public String checkIsEmailVerified(long userId) {

         return String.valueOf(userService.findByUserId(userId));
    }
}
