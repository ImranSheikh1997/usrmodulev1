package com.usermodule.websocketutil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class WebSocketController {

    @Autowired
    private WebSocketResponse webSocketResponse;

//    @Autowired
//    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String checkUserVerified(String userId)
    throws Exception{
        Thread.sleep(3000); //will send response in interval of every 3 seconds
        boolean isVerified = webSocketResponse.checkIsEmailVerified(Long.parseLong(userId));
        return HtmlUtils.htmlEscape(Boolean.toString(isVerified));
    }
}
