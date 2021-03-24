package com.usermodule.utility.sms;

import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TwilioIntializer {
    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioIntializer(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;

        Twilio.init(twilioConfig.getAccount_sid(),twilioConfig.getAuth_token());
        log.info("Twilio initialize with account {} ",twilioConfig.getAccount_sid());
    }
}
