package com.usermodule.smsutil.twilioutil;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import com.usermodule.smsutil.dto.SmsRequest;
import com.usermodule.smsutil.dto.SmsSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Configuration
public class TwilioSmsSender implements SmsSender {

    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioSmsSender(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {

        if(isMobileNumberValid(smsRequest.getMobileNumber())){
            PhoneNumber to = new PhoneNumber(smsRequest.getMobileNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrial_number());
            String msg = smsRequest.getMessage();
            MessageCreator creator= Message.creator(to, from, msg);
            creator.create();

            log.info("Send SMS {} "+smsRequest);
        }
        else{
            new IllegalStateException(
                    "Mobile Number [ " + smsRequest.getMobileNumber() +"] is not valid number"
            );
        }
    }

    private boolean isMobileNumberValid(String mobileNumber) {
        //International Mobile number validation
        Pattern pattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }
}
