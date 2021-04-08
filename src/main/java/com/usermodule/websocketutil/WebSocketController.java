package com.usermodule.websocketutil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
@Slf4j
public class WebSocketController {

    @Autowired
    private WebSocketResponse webSocketResponse;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

  @GetMapping("/home")
    public void checkUserVerified()
            //@Payload String userId) throws Exception
    {
//        Thread.sleep(3000); //simulated delay
//        String id = new Gson().fromJson(userId, Map.class).get("name").toString();
        //log.info(" userId ",userId);
//        String s1 = webSocketResponse.checkIsEmailVerified(Long.parseLong(id));
        messagingTemplate.convertAndSendToUser(HtmlUtils.htmlEscape("1"),"/queue/notification","Test");
    //    messagingTemplate.convertAndSend(s1);

    }
}
