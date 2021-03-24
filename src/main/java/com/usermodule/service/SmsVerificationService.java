package com.usermodule.service;

import com.usermodule.dto.sms.SmsRequest;
import com.usermodule.dto.sms.SmsSender;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SmsVerificationService {

    private SmsSender smsSender;

    public void sendSms(SmsRequest smsRequest){
        smsSender.sendSms(smsRequest);
    }
}
